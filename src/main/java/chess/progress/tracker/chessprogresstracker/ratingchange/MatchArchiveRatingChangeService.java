package chess.progress.tracker.chessprogresstracker.ratingchange;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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

        final LocalDate lowerBound = LocalDate.ofInstant(start, timezoneService.getZoneId());
        final LocalDate upperBound = LocalDate.ofInstant(end, timezoneService.getZoneId());
        final List<Match> matches = new ArrayList<>();

        if (rangeIsContainedWithinASingleMonth(lowerBound, upperBound)) {
            matches.addAll(matchArchiveService.getAllMatchesForMonthAndYear(username, lowerBound));
        } else {
            long monthsBetween = ChronoUnit.MONTHS.between(lowerBound, upperBound);

            for (int i = 0; i < monthsBetween; i++) {
                final LocalDate newDateToQuery = lowerBound.plusMonths(i);
                matches.addAll(matchArchiveService.getAllMatchesForMonthAndYear(username, newDateToQuery));
            }
        }

        final Map<String, List<Match>> matchesByDiscipline = matches.stream()
                .filter(match -> doesMatchLayWithinSpecifiedRange(match, start, end))
                .collect(Collectors.groupingBy(Match::getTime_class));

        return ratingChangeSet;
    }

    private boolean rangeIsContainedWithinASingleMonth(LocalDate lower, LocalDate upper) {
        return lower.getMonthValue() == upper.getMonthValue() && lower.getYear() == upper.getYear();
    }

    private boolean doesMatchLayWithinSpecifiedRange(Match match, Instant lower, Instant upper) {
        return lower.compareTo(match.getEnd_time()) <= 0 && upper.compareTo(match.getEnd_time()) >= 0;
    }
}
