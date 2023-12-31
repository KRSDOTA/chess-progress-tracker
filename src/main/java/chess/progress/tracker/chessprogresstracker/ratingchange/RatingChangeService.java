package chess.progress.tracker.chessprogresstracker.ratingchange;

import java.time.Instant;
import java.util.Set;

/**
 * Specify operations for computing changes in rating
 */
public interface RatingChangeService {

    /**
     * Computes a set of rating changes, each element being a rating change object, one for each discipline
     * @param username username to get ratings for
     * @param start closed interval lower bound
     * @param end closed interval upper bound
     */
    Set<RatingChange> getCrossDisciplineChangesForInterval(String username, Instant start, Instant end);

}
