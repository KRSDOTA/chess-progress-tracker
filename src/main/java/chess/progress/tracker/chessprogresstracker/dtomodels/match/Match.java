package chess.progress.tracker.chessprogresstracker.dtomodels.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Match {
    private String url;

    private String pgn;

    private Integer time_control;

    private Instant end_time;

    private Boolean rated;

    private Accuracies accuracies;

    private String tcn;

    private UUID uuid;

    private String initial_setup;

    private String fen;

    private String time_class;

    private String rules;

    private PlayerDetails white;

    private PlayerDetails black;
}
