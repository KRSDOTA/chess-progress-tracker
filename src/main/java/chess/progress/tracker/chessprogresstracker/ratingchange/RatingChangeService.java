package chess.progress.tracker.chessprogresstracker.ratingchange;

import java.time.Instant;
import java.util.Set;

/**
 * Specify operations for computing changes in rating
 */
public interface RatingChangeService {

    /**
     *
     * @param username
     * @param start
     * @param end
     * @return
     */
    Set<RatingChange> getCrossDisciplineChangesForInterval(String username, Instant start, Instant end);

}
