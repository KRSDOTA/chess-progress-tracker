package statistics;

import chess.progress.tracker.chessprogresstracker.dtomodels.statistics.Stats;
import chess.progress.tracker.chessprogresstracker.statistics.StatisticsController;
import chess.progress.tracker.chessprogresstracker.statistics.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {

    @InjectMocks
    private StatisticsController statisticsController;

    @Mock
    private StatisticsService statisticsService;

    @Test
    void shouldDelegateToStatisticsServiceToGetStats() {
        statisticsController.getPlayerStats("test");

        verify(statisticsService).getStatistics("test");
    }

    @Test
    void shouldReturnEmptyStatsObjectWhenRestClientExceptionIsHandled() {
        RestClientException restClientException = new RestClientException("yolo");

        ResponseEntity<Stats> response = statisticsController.handleThrownRestClientException(restClientException);

        assertThat(response.getBody()).isEqualTo(new Stats());
    }

    @Test
    void shouldReturnEmptyStatsObjectWhenHttpClientExceptionIsHandled(){
        RestClientException httpClientErrorException = new HttpClientErrorException(HttpStatus.NOT_FOUND);

        ResponseEntity<Stats> response = statisticsController.handleThrownRestClientException(httpClientErrorException);

        assertThat(response.getBody()).isEqualTo(new Stats());
    }

}
