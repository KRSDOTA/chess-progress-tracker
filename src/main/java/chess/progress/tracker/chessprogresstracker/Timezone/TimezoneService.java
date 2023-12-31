package chess.progress.tracker.chessprogresstracker.Timezone;

import org.springframework.stereotype.Component;

import java.time.ZoneId;

public class TimezoneService {

    private ZoneId zoneId;

    public TimezoneService(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }
}
