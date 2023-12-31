package chess.progress.tracker.chessprogresstracker.config;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;
import chess.progress.tracker.chessprogresstracker.ratingchange.MatchArchiveRatingChangeService;
import chess.progress.tracker.chessprogresstracker.ratingchange.RatingChangeService;
import org.springframework.context.annotation.Bean;

public class RatingChangeConfig {

    @Bean
    public RatingChangeService ratingChangeService(MatchArchiveService matchArchiveService, TimezoneService timezoneService) {
        return new MatchArchiveRatingChangeService(matchArchiveService, timezoneService);
    }

}
