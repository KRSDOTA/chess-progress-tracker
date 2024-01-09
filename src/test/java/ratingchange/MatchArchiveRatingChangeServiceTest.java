package ratingchange;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;
import chess.progress.tracker.chessprogresstracker.ratingchange.MatchArchiveRatingChangeService;
import chess.progress.tracker.chessprogresstracker.ratingchange.RatingChange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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

    private final String username = "hikaru"; // has to be this in order to match the test data

    @BeforeEach
    void setUp() throws IOException {
        when(timezoneService.getZoneId()).thenReturn(zoneId);
        matchData = JsonTestDataParser.parseMatchData("src/test/resources/hikaruMatchData.json");
    }

    @Test
    void ratingChangeSetShouldNotBeEmptyAndHaveSizeOfThree() {
        final LocalDate lowerBoundDate = LocalDate.of(2023, 12, 20);
        final LocalDate upperBoundDate = LocalDate.of(2023, 12, 28);

        when(matchArchiveService.getAllMatchesForMonthAndYear(eq(username), any())).thenReturn(matchData);

        final Set<RatingChange> actualRatingChangeSet = matchArchiveRatingChangeService.getCrossDisciplineChangesForInterval(username, lowerBoundDate, upperBoundDate);

        assertThat(actualRatingChangeSet).isNotEmpty();
        assertThat(actualRatingChangeSet).hasSize(3);
    }

    @Test 
    void whenRangeIsContainedWithinASingleMonthThenMatchArchiveServiceShouldOnlyBeCalledOnce() {
        final LocalDate lowerBoundDate = LocalDate.of(2023, 12, 20);
        final LocalDate upperBoundDate = LocalDate.of(2023, 12, 28);

        when(matchArchiveService.getAllMatchesForMonthAndYear(eq(username), any())).thenReturn(matchData);

        matchArchiveRatingChangeService.getCrossDisciplineChangesForInterval(username, lowerBoundDate, upperBoundDate);

        // verify that the match archive service was only called once
        verify(matchArchiveService).getAllMatchesForMonthAndYear(eq(username), any());
    }

    @Test 
    void whenRangeIsNotContainedWithinASingleMonthThenMatchArchiveServiceShouldBeCalledMultipleTimes() {
        final LocalDate lowerBoundDate = LocalDate.of(2023, 12, 20);
        final LocalDate upperBoundDate = LocalDate.of(2024, 1, 28);

        when(matchArchiveService.getAllMatchesForMonthAndYear(eq(username), any())).thenReturn(matchData);

        matchArchiveRatingChangeService.getCrossDisciplineChangesForInterval(username, lowerBoundDate, upperBoundDate);

        // verify service was called twice, one for each month between the lower and upper bound inclusive
        verify(matchArchiveService, times(2)).getAllMatchesForMonthAndYear(eq(username), any());
    }

}
