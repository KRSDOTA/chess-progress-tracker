package chess.progress.tracker.chessprogresstracker.progress;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @GetMapping
    public ResponseEntity<String> getProgress() {
        return ResponseEntity.ok("progress");
    }
}
