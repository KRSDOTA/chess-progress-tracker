package chess.progress.tracker.chessprogresstracker.dtomodels.game;

import lombok.Data;

/**
 *  *   "record": { // summary of all games played
 *  *     "win": 177,  // number of games won
 *  *     "loss": 124, // number of games lost
 *  *     "draw": 21,  // number of games drawn
 *  *     "time_per_move": 18799, // integer number of seconds per average move
 *  *     "timeout_percent": 9.99 // timeout percentage in the last 90 days
 *  *   },
 */
@Data
public class Record {

    private final Integer win;

    private final Integer loss;

    private final Integer time_per_move;

    private final Double timeout_percentage;
}
