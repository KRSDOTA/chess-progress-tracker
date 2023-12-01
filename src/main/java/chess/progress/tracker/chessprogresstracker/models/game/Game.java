package chess.progress.tracker.chessprogresstracker.models.game;

import lombok.Data;

@Data
public class Game {

    private final Last last;

    private final Best best;

    private final Record record;

    private final Tornament tornament;

}
