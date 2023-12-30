package chess.progress.tracker.chessprogresstracker.matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Games;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.List;

public class ChessPubApiMatchArchiveService implements MatchArchiveService {

    private final RestTemplate restTemplate;

    public ChessPubApiMatchArchiveService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Match> getMostRecentMatches(String username, Integer numberOfGames) {
        final String url = buildGamesUrl(username);
        final Games games = restTemplate.getForObject(url, Games.class);
        return games.getGames();
    }

    private String buildGamesUrl(String username) {
        final Instant currentTime = Instant.now();
        final LocalDate currentDate = LocalDate.ofInstant(currentTime, ZoneId.of("UTC+1"));
        final StringBuilder matchUrlBuilder = new StringBuilder();
        return matchUrlBuilder
                .append("/player/")
                .append(username)
                .append("/games/")
                .append("/")
                .append(currentDate.getYear())
                .append("/")
                .append(getZeroPaddedMonthValue(currentDate))
                .toString();
    }

    private String getZeroPaddedMonthValue(LocalDate date) {
        if(date.getMonthValue() < 10 ) {
            return "0" + date.getMonthValue();
        }

        return String.valueOf(date.getMonthValue());
    }

    @Override
    public List<Match> getAllMatches(String username, Month month, Year year) {
        return null;
    }
}
