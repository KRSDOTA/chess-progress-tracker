package chess.progress.tracker.chessprogresstracker.statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.Stats;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatisticsService {

    private final RestTemplate restTemplate;

    public StatisticsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get the statistics for a given username
     * @param username username to get statistics for
     * @return Stats object
     */
    public Stats getStatistics(String username) {
        final String url = "/player/" + username + "/stats";
        return restTemplate.getForObject(url, Stats.class);
    }

}
