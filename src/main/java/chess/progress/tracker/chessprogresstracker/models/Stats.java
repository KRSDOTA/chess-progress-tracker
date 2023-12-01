package chess.progress.tracker.chessprogresstracker.models;


import chess.progress.tracker.chessprogresstracker.models.game.Game;
import lombok.Data;
import org.springframework.lang.Nullable;

/**
 *  win/loss, and other stats about a player's game play, tactics, lessons and Puzzle Rush score.
 */
@Data
public class Stats {

    @Nullable
    private final Game chessDaily;

    @Nullable
    private final Game chess960Daily;

    @Nullable
    private final Game chessRapid;

    @Nullable
    private final Game chessBullet;

    @Nullable
    private final Game chessBlitz;

    @Nullable
    private final Integer fide;

    @Nullable
    private final Tactics tactics;

    @Nullable
    private final PuzzleRush puzzleRush;

}
