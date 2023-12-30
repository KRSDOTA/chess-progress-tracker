package chess.progress.tracker.chessprogresstracker.matcharchive;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MatchEndpointUrlBuilder {

    public String buildGamesUrl(String username, LocalDate date) {
        final StringBuilder matchUrlBuilder = new StringBuilder();
        return matchUrlBuilder
                .append("/player/")
                .append(username)
                .append("/games/")
                .append(date.getYear())
                .append("/")
                .append(getZeroPaddedMonthValue(date))
                .toString();
    }

    private String getZeroPaddedMonthValue(LocalDate date) {
        if(date.getMonthValue() < 10 ) {
            return "0" + date.getMonthValue();
        }

        return String.valueOf(date.getMonthValue());
    }

}
