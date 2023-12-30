package chess.progress.tracker.chessprogresstracker.matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Games;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessPubApiMatchArchiveService implements MatchArchiveService {

    private final RestTemplate restTemplate;
    private final MatchEndpointUrlBuilder matchEndpointUrlBuilder;

    public ChessPubApiMatchArchiveService(RestTemplate restTemplate,
                                          MatchEndpointUrlBuilder matchEndpointUrlBuilder) {
        this.restTemplate = restTemplate;
        this.matchEndpointUrlBuilder = matchEndpointUrlBuilder;
    }

    @Override
    public List<Match> getMostRecentMatches(String username, Integer numberOfGames) {
        final List<Match> matches = new ArrayList<>();
        int monthQueryOffset = 0;

        while (matches.size() < numberOfGames) {
         final LocalDate dateToQuery = LocalDate.now().minusMonths(monthQueryOffset);
         matches.addAll(getGamesFromChessApi(username, dateToQuery));
         monthQueryOffset++;
        }

        // discard any of the tail instances we don't care about
        return matches.subList(0, numberOfGames);
    }

    private List<Match> getGamesFromChessApi(String username, LocalDate date) {
        final String url = matchEndpointUrlBuilder.buildGamesUrl(username, date);
        final Games games = restTemplate.getForObject(url, Games.class);
        return games == null ? Collections.emptyList() : games.getGames().stream().sorted((match1, match2) ->  match2.getEnd_time().getNano() - match1.getEnd_time().getNano()).toList();
    }

    @Override
    public List<Match> getAllMatches(String username, LocalDate localDate) {
        return getGamesFromChessApi(username, localDate);
    }
}
