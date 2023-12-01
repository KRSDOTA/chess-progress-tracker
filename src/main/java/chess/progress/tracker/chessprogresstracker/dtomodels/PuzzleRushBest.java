package chess.progress.tracker.chessprogresstracker.dtomodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PuzzleRushBest {

    private Integer total_attempts;

    private Integer score;
}
