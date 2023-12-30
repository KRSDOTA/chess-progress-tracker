package matcharchive;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Games;
import chess.progress.tracker.chessprogresstracker.matcharchive.ChessPubApiMatchArchiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChessPubApiMatchServiceTest {

    @InjectMocks
    private ChessPubApiMatchArchiveService chessPubApiMatchArchiveService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void shouldConstructCorrectUrl() {
        final String username = "something";
        final Instant currentTime = Instant.now();
        final LocalDate expectedCurrentDate = LocalDate.ofInstant(currentTime, ZoneId.of("UTC+1"));
        final Games fakeGames = new Games();
        fakeGames.setGames(Collections.emptyList());
        when(restTemplate.getForObject(any(), any())).thenReturn(fakeGames);

        chessPubApiMatchArchiveService.getMostRecentMatches(username, 10);

        verify(restTemplate).getForObject("/player/something/" + expectedCurrentDate.getMonthValue() + "/" + expectedCurrentDate.getYear(), Games.class);
    }

}
