package chess.progress.tracker.chessprogresstracker.ratingchange;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/rating-delta")
@CrossOrigin(value = "http://localhost:3000")
public class RatingChangeController {

    private final RatingChangeService ratingChangeService;

    public RatingChangeController(RatingChangeService ratingChangeService) {
        this.ratingChangeService = ratingChangeService;
    }

    @GetMapping("/{username}/start/{start}/end/{end}")
    public ResponseEntity<Set<RatingChange>> getRatingChangesAcrossDisciplines(@PathVariable String username, @PathVariable String start, @PathVariable String end) {
        final Set<RatingChange> ratingChangeSet = ratingChangeService.getCrossDisciplineChangesForInterval(username, Instant.parse(start), Instant.parse(end));
        return ResponseEntity.ok(ratingChangeSet);
    }

}
