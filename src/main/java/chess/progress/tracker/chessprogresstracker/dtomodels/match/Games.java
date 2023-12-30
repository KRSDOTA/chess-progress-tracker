package chess.progress.tracker.chessprogresstracker.dtomodels.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Games {
    private List<Match> games;
}
