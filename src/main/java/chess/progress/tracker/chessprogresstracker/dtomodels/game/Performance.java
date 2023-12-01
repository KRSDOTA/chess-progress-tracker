package chess.progress.tracker.chessprogresstracker.dtomodels.game;

import lombok.Data;

import java.time.Instant;

@Data
public class Performance {

    private final Instant date;

    private final Integer rating;
}
