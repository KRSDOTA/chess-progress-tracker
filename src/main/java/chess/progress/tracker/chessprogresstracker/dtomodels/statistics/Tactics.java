package chess.progress.tracker.chessprogresstracker.dtomodels.statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.game.Performance;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tactics {
    private Performance highest;

    private Performance lowest;
}
