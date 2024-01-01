package chess.progress.tracker.chessprogresstracker.config;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

@Configuration
public class TimezoneConfig {

    /**
     * Create a bean which enables custom timezone setting. Not actually sure how best to do this to be
     * completely honest, doubt this is something we want to configure on the server side, as we should probably be
     * having the client tell us what time zone they're using!
     *
     * @return an instance of the TimezoneService where the ZoneId is specified.
     */
    @Bean
    public TimezoneService timezoneService(){
        return new TimezoneService(ZoneId.of("UTC+1"));
    }

}
