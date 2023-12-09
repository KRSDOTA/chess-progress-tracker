package chess.progress.tracker.chessprogresstracker.statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.Stats;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(value = "http://localhost:3000")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Stats> getPlayerStats(@PathVariable String username) {
        final Stats stats = statisticsService.getStatistics(username);
        return ResponseEntity.ok(stats);
    }

}
