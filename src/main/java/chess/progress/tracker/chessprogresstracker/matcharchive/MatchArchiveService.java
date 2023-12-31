package chess.progress.tracker.chessprogresstracker.matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

public interface MatchArchiveService {

    /**
     * Gets the most recent matches in ascending order
     * @param username username of chess player to get data for
     * @param numberOfGames the number of games to get
     */
    List<Match> getMostRecentMatches(String username, Integer numberOfGames);

    /**
     * Gets the previous 30 days worth of match data for the given player in ascending order
     * @param username username of chess player to get data for
     * @param localDate startingPoint
     */
    List<Match> getAllMatches(String username, LocalDate localDate);

}
