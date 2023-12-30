package chess.progress.tracker.chessprogresstracker.statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.Stats;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ChessApiStatsService implements StatisticsService {

    private final RestTemplate restTemplate;

    public ChessApiStatsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Stats getStatistics(String username) throws RestClientException {
        final String url = "/player/" + username + "/stats";
        return restTemplate.getForObject(url, Stats.class);
    }

}
