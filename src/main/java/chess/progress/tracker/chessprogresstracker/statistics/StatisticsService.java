package chess.progress.tracker.chessprogresstracker.statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.statistics.Stats;

public interface StatisticsService {

    /**
     * Get the statistics for a given username
     * @param username username to get statistics for
     * @return Stats object
     */
    Stats getStatistics(String username);

}
