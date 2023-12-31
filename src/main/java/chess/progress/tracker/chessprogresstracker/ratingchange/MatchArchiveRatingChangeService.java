package chess.progress.tracker.chessprogresstracker.ratingchange;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MatchArchiveRatingChangeService implements RatingChangeService {

    private final MatchArchiveService matchArchiveService;

    private final TimezoneService timezoneService;

    public MatchArchiveRatingChangeService(MatchArchiveService matchArchiveService, TimezoneService timezoneService) {
        this.matchArchiveService = matchArchiveService;
        this.timezoneService = timezoneService;
    }

    @Override
    public Set<RatingChange> getCrossDisciplineChangesForInterval(String username, Instant start, Instant end) {
        final Set<RatingChange> ratingChangeSet = new HashSet<>();

        final LocalDate localDateStart = LocalDate.ofInstant(start, timezoneService.getZoneId());
        final LocalDate localDateEnd = LocalDate.ofInstant(end, timezoneService.getZoneId());

        matchArchiveService.getAllMatches(username, localDateStart);
        matchArchiveService.getAllMatches(username, localDateEnd);

        return ratingChangeSet;
    }
}
