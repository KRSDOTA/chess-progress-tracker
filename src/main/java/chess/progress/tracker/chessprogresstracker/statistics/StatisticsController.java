package chess.progress.tracker.chessprogresstracker.statistics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getPlayerStats(@PathVariable String username) {
        String stats = statisticsService.getStatistics(username);
        return ResponseEntity.ok(stats);
    }

}
