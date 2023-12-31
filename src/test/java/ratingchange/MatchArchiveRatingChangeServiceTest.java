package ratingchange;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;
import chess.progress.tracker.chessprogresstracker.ratingchange.MatchArchiveRatingChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchArchiveRatingChangeServiceTest {

    @InjectMocks
    private MatchArchiveRatingChangeService matchArchiveRatingChangeService;

    @Mock
    private MatchArchiveService matchArchiveService;

    @Mock
    private TimezoneService timezoneService;

    private final ZoneId zoneId = ZoneId.of("UTC+1");

    private List<Match> matchData;
    @BeforeEach
    void setUp() throws IOException {
        when(timezoneService.getZoneId()).thenReturn(zoneId);
        matchData = JsonTestDataParser.parseMatchData("src/test/resources/hikaruMatchData.json");
    }

    @Test
    void shouldCorrectlyConstructRatingChangeSetFromMatchData() {
        final String username = "something";
        final Instant lowerBound = Instant.now().minusSeconds(60*60*24*2);
        final Instant upperBound = Instant.now();

        when(matchArchiveRatingChangeService.getCrossDisciplineChangesForInterval(username, lowerBound, upperBound)).thenReturn(null);
    }

}
