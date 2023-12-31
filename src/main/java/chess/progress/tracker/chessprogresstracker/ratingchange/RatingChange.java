package chess.progress.tracker.chessprogresstracker.ratingchange;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingChange {
    private Discipline discipline;

    private int[] points;
}
