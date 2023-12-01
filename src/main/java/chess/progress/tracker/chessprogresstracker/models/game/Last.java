package chess.progress.tracker.chessprogresstracker.models.game;

import lombok.Data;

import java.time.Instant;

/**
 *  *   "last": { // the current stats
 *  *     "date": 1509709165, // timestamp of the last rated game finished
 *  *     "rating": 1642, // most-recent rating
 *  *     "rd": 58 // the Glicko "RD" value used to calculate ratings changes
 *  *   },
 */
@Data
public class Last {

    private final Instant date;

    private final Integer rating;

    private final Integer rd;
}
