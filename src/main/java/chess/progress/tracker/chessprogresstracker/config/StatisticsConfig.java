package chess.progress.tracker.chessprogresstracker.config;

import chess.progress.tracker.chessprogresstracker.statistics.DelegateToChessPubAPIService;
import chess.progress.tracker.chessprogresstracker.statistics.StatisticsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StatisticsConfig {

    @Bean
    @ConditionalOnProperty("chesspubapi.enabled")
    public StatisticsService getDelegateStatsService(RestTemplate restTemplate) {
        return new DelegateToChessPubAPIService(restTemplate);
    }

}
