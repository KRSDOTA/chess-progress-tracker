package chess.progress.tracker.chessprogresstracker.dtomodels.match;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDetails {

    private String username;

    private Integer rating;

    private String result;

    private String id;
}
