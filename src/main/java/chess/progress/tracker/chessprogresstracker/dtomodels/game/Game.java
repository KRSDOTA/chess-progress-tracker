package chess.progress.tracker.chessprogresstracker.dtomodels.game;

import lombok.Data;

@Data
public class Game {

    private final Last last;

    private final Best best;

    private final Record record;

    private final Tornament tornament;

}
