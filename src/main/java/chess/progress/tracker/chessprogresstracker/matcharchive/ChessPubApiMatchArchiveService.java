package chess.progress.tracker.chessprogresstracker.matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Games;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import org.springframework.web.client.RestTemplate;

import java.time.*;
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
        final String url = matchEndpointUrlBuilder.buildGamesUrl(username, LocalDate.now());
        final Games games = restTemplate.getForObject(url, Games.class);
        return games == null ? Collections.emptyList() : games.getGames();
    }


    @Override
    public List<Match> getAllMatches(String username, Month month, Year year) {
        return null;
    }
}
