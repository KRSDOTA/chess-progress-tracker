package chess.progress.tracker.chessprogresstracker.ratingchange;

import lombok.Getter;

@Getter
public enum Discipline {
    RAPID("rapid"),
    BLITZ("blitz"),
    BULLET("bullet");

    private final String disciplineName;

    Discipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

}
