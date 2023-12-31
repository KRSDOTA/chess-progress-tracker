package ratingchange;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;
import chess.progress.tracker.chessprogresstracker.ratingchange.MatchArchiveRatingChangeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MatchArchiveRatingChangeServiceTest {

    @InjectMocks
    private MatchArchiveRatingChangeService matchArchiveRatingChangeService;

    @Mock
    private MatchArchiveService matchArchiveService;

    @Mock
    private TimezoneService timezoneService;

    


}
