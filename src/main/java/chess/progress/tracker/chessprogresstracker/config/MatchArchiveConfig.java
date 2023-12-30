package chess.progress.tracker.chessprogresstracker.config;

import chess.progress.tracker.chessprogresstracker.matcharchive.ChessPubApiMatchArchiveService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchEndpointUrlBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MatchArchiveConfig {

    @Bean
    public MatchArchiveService createMatchArchiveService(RestTemplate restTemplate, MatchEndpointUrlBuilder matchEndpointUrlBuilder) {
        return new ChessPubApiMatchArchiveService(restTemplate, matchEndpointUrlBuilder);
    }

}
