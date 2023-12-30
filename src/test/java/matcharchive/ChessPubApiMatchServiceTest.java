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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    void chessApiShouldNotBeCalledWhenNoGamesSpecified() {
        chessPubApiMatchArchiveService.getMostRecentMatches("something", 0);

        verify(restTemplate, never()).getForObject(any(), any());
    }

    @Test
    void shouldReQueryUntilSurplusOfGameDataReturned() {
        final String username = "something";
        final String fakeUrl = "/player/" + username + "/games/2023/10";

        Games fakeGames = new Games();
        fakeGames.setGames(List.of(new Match(), new Match()));

        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        when(restTemplate.getForObject(fakeUrl, Games.class)).thenReturn(fakeGames);

        chessPubApiMatchArchiveService.getMostRecentMatches(username, 3);

        verify(restTemplate, times(2)).getForObject(fakeUrl, Games.class);
    }


    @Test
    void shouldCutDownSurplusMatchDataToMatchNumberSpecifiedInRequest() {
        final String username = "something";
        final String fakeUrl = "/player/" + username + "/games/2023/10";

        Games fakeGames = new Games();
        fakeGames.setGames(List.of(new Match(), new Match()));

        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);
        when(restTemplate.getForObject(fakeUrl, Games.class)).thenReturn(fakeGames);

       final List<Match> matchList = chessPubApiMatchArchiveService.getMostRecentMatches(username, 3);

       assertThat(matchList).hasSize(3);
    }

}
