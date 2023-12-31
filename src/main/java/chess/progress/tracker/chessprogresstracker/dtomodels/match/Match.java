package chess.progress.tracker.chessprogresstracker.dtomodels.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Match implements Comparable<Match> {
    private String url;

    private String pgn;

    private String time_control;

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

    @Override
    public int compareTo(Match match) {
        return match.getEnd_time().compareTo(getEnd_time());
    }
}
