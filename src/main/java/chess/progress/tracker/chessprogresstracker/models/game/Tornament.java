package chess.progress.tracker.chessprogresstracker.models.game;

import lombok.Data;

/**
 *  *   "tournament": { // summary of tournaments participated in
 *  *     "count": 20,   // number of tournaments joined
 *  *     "withdraw": 1, // number of tournaments withdrawn from
 *  *     "points": 39,  // total number of points earned in tournaments
 *  *     "highest_finish": 1 // best tournament place
 *  *   }
 */
@Data
public class Tornament {

    private final Integer count;

    private final Integer withdraw;

    private final Integer points;

    private final Integer highestFinish;
}
