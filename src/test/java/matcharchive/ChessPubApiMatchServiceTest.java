package matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Games;
import chess.progress.tracker.chessprogresstracker.matcharchive.ChessPubApiMatchArchiveService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchEndpointUrlBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChessPubApiMatchServiceTest {

    @InjectMocks
    private ChessPubApiMatchArchiveService chessPubApiMatchArchiveService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MatchEndpointUrlBuilder matchEndpointUrlBuilder;

    @Test
    void ensureRestTemplateIsCalledCorrectly() {
        final String username = "something";
        final String fakeUrl = "/player/" + username + "/games/2023/10";
        when(matchEndpointUrlBuilder.buildGamesUrl(eq(username), any())).thenReturn(fakeUrl);

        chessPubApiMatchArchiveService.getMostRecentMatches(username, 10);

        verify(restTemplate).getForObject(fakeUrl, Games.class);
    }

}
