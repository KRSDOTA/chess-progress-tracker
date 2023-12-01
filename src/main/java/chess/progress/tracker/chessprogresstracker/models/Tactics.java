package chess.progress.tracker.chessprogresstracker.models;

import chess.progress.tracker.chessprogresstracker.models.game.Performance;
import lombok.Data;

@Data
public class Tactics {
    private final Performance highest;

    private final Performance lowest;
}
