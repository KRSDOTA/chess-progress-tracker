package chess.progress.tracker.chessprogresstracker.models.game;

import lombok.Data;

import java.time.Instant;

/**
 * *   "best": { // the best rating achieved by a win
 * *     "date": 1256228875, // timestamp of the best-win game
 * *     "rating": 2065, // highest rating achieved
 * *     "game": "URL" // URL of the best-win game
 * *   },
 */
@Data
public class Best {

    private final Instant date;

    private final Integer rating;

    private final String game;

}
