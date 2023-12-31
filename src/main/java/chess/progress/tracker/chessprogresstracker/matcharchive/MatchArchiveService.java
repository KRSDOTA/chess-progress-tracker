package chess.progress.tracker.chessprogresstracker.matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;

import java.time.LocalDate;
import java.util.List;

public interface MatchArchiveService {

    /**
     * Gets the most recent matches in ascending order
     * @param username username of chess player to get data for
     * @param numberOfGames the number of games to get
     */
    List<Match> getMostRecentMatches(String username, Integer numberOfGames);

    /**
     * Gets the specified month's worth of match data for the given player, sorted in ascending order
     * @param username username of chess player to get data for
     * @param localDate to specify the month and year for fetching data
     */
    List<Match> getAllMatchesForMonthAndYear(String username, LocalDate localDate);

}
