package matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Games;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import chess.progress.tracker.chessprogresstracker.matcharchive.ChessPubApiMatchArchiveService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchEndpointUrlBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChessPubApiMatchServiceTest {

    @InjectMocks
    private ChessPubApiMatchArchiveService chessPubApiMatchArchiveService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MatchEndpointUrlBuilder matchEndpointUrlBuilder;

    @Test
    void chessApiShouldNotBeCalledWhenZeroGamesPassed() {
        chessPubApiMatchArchiveService.getMostRecentMatches("something", 0);

        verify(restTemplate, never()).getForObject(any(), any());
    }

    @Test
    void shouldReQueryUntilSurplusOfGameDataReturned() {
        final String username = "something";
        final String fakeUrl = "/player/" + username + "/games/2023/10";
        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        returnFakeGamesWithUrlCall(fakeUrl);

        chessPubApiMatchArchiveService.getMostRecentMatches(username, 3);

        verify(restTemplate, times(2)).getForObject(fakeUrl, Games.class);
    }

    @Test
    void shouldCutDownSurplusMatchDataToMatchNumberSpecifiedInRequest() {
        final String username = "something";
        final String fakeUrl = "/player/" + username + "/games/2023/10";
        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        returnFakeGamesWithUrlCall(fakeUrl);

       final List<Match> matchList = chessPubApiMatchArchiveService.getMostRecentMatches(username, 3);

       assertThat(matchList).hasSize(3);
    }

    @Test
    void shouldSuccessfullyGetAllMatchesForAGivenMonthAndYear() {
        final String username = "something";
        final LocalDate localDate = LocalDate.of(2000, 1, 12);
        final String fakeUrl = "/player/" + username + "/games/" + localDate.getYear() + "/" + localDate.getMonthValue();
        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        returnFakeGamesWithUrlCall(fakeUrl);

        final List<Match> matchList = chessPubApiMatchArchiveService.getAllMatchesForMonthAndYear(username, localDate);

        assertThat(matchList).hasSize(2);
    }

    @Test
    void shouldSuccessfullySortInDescendingOrder() {
        final String username = "something";
        final LocalDate localDate = LocalDate.of(2000, 1, 12);
        final String fakeUrl = "/player/" + username + "/games/" + localDate.getYear() + "/" + localDate.getMonthValue();
        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        returnFakeGamesWithUrlCall(fakeUrl);

        final List<Match> matchList = chessPubApiMatchArchiveService.getAllMatchesForMonthAndYear(username, localDate);

        assertThat(matchList.get(0).compareTo(matchList.get(1))).isEqualTo(-1);
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenGamesIsNull() {
        final String username = "something";
        final LocalDate localDate = LocalDate.of(2000, 1, 12);
        final String fakeUrl = "/player/" + username + "/games/" + localDate.getYear() + "/" + localDate.getMonthValue();
        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        when(restTemplate.getForObject(fakeUrl, Games.class)).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> chessPubApiMatchArchiveService.getMostRecentMatches(username, 1));
    }


    private void returnFakeGamesWithUrlCall(String fakeUrl){
        Games fakeGames = new Games();
        final Match match1 = new Match();
        match1.setEnd_time(Instant.ofEpochSecond(LocalDate.of(2000, 10, 12).toEpochSecond(LocalTime.now(), ZoneOffset.UTC)));
        final Match match2 = new Match();
        match2.setEnd_time(Instant.now());
        final ArrayList<Match> games = new ArrayList<>(List.of(match1, match2));
        fakeGames.setGames(games);
        when(restTemplate.getForObject(fakeUrl, Games.class)).thenReturn(fakeGames);
    }

}
