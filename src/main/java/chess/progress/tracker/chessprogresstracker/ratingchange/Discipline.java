package chess.progress.tracker.chessprogresstracker.ratingchange;

public enum Discipline {
    RAPID("rapid"),
    BLITZ("blitz"),
    BULLET("bullet");

    private final String disciplineName;

    Discipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }
}
