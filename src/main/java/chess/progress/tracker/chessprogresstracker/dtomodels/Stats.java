package chess.progress.tracker.chessprogresstracker.dtomodels;


import chess.progress.tracker.chessprogresstracker.dtomodels.game.Game;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * win/loss, and other stats about a player's game play, tactics, lessons and Puzzle Rush score.
 */
@Data
@NoArgsConstructor
public class Stats {

    
    private Game chess_daily;

    
    private Game chess_960_daily;

    
    private Game chess_rapid;

    
    private Game chess_bullet;

    
    private Game chess_blitz;

    
    private Integer fide;

    
    private Tactics tactics;

    
    private PuzzleRush puzzle_rush;

}
