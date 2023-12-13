package chess.progress.tracker.chessprogresstracker.statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.Stats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

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
        return ResponseEntity.ok(statisticsService.getStatistics(username));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Stats> handleThrownRestClientException(RestClientException restClientException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Stats());

    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Stats> handleThrownRestClientException(HttpClientErrorException httpClientErrorException) {
        return ResponseEntity
                .status(httpClientErrorException.getStatusCode())
                .body(new Stats());

    }
}
