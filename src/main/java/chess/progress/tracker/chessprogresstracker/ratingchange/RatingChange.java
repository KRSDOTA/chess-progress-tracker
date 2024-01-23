package chess.progress.tracker.chessprogresstracker.ratingchange;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RatingChange {
    private Discipline discipline;

    private List<Point> points;
}
