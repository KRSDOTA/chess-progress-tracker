package chess.progress.tracker.chessprogresstracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgressController {

    @GetMapping("/")
    public ResponseEntity<String> getProgress() {
        return ResponseEntity.ok("progress");
    }
}
