package chess.progress.tracker.chessprogresstracker.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    private static final String CHESS_API = "https://api.chess.com/pub";

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplateBuilder().rootUri(CHESS_API).build();
    }

}
