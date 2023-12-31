package chess.progress.tracker.chessprogresstracker.matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@CrossOrigin(value = "http://localhost:3000")
public class MatchArchiveServiceController {

    private final MatchArchiveService matchArchiveService;

    public MatchArchiveServiceController(MatchArchiveService matchArchiveService) {
        this.matchArchiveService = matchArchiveService;
    }

    @GetMapping("/{username}/{numberOfGames}")
    public ResponseEntity<List<Match>> getRecentMatches(@PathVariable String username, @PathVariable Integer numberOfGames) {
        final List<Match> matches = matchArchiveService.getMostRecentMatches(username, numberOfGames);
        return ResponseEntity.ok(matches);
    }
}
