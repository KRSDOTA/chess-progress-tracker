package matcharchive;

import chess.progress.tracker.chessprogresstracker.matcharchive.MatchEndpointUrlBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MatchEndpointUrlBuilderTest {

    @InjectMocks
    private MatchEndpointUrlBuilder matchEndpointUrlBuilder;

    @Test
    void shouldConstructUrl() {
        final String username = "something";
        final LocalDate expectedCurrentDate = LocalDate.of(2022, 12, 11);

        final String expectedUrl = "/player/" + username + "/games/" +  expectedCurrentDate.getYear() + "/" + expectedCurrentDate.getMonthValue();

        final String actualUrl = matchEndpointUrlBuilder.buildGamesUrl(username, expectedCurrentDate);

        assertThat(actualUrl).isEqualTo(expectedUrl);
    }


    @Test
    void shouldSuccessfullyPadMonthIfSingleDigit() {
        final String username = "something";
        final LocalDate expectedCurrentDate = LocalDate.of(2000, 9, 12);

        final String expectedUrl = "/player/" + username + "/games/" +  expectedCurrentDate.getYear() + "/" + "0" + expectedCurrentDate.getMonthValue();

        final String actualUrl = matchEndpointUrlBuilder.buildGamesUrl(username, expectedCurrentDate);

        assertThat(actualUrl).isEqualTo(expectedUrl);
    }

}
