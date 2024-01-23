package chess.progress.tracker.chessprogresstracker.ratingchange;

import chess.progress.tracker.chessprogresstracker.Timezone.TimezoneService;
import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import chess.progress.tracker.chessprogresstracker.matcharchive.MatchArchiveService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public Set<RatingChange> getCrossDisciplineChangesForInterval(String username, LocalDate lowerBound, LocalDate upperBound) {
        final List<Match> matches = getAllMatchesForDefinedInterval(username, lowerBound, upperBound);

        final Map<String, List<Match>> matchesByDiscipline = filterMatchesOutsideTimeRangeAndGroupByDiscipline(matches, lowerBound, upperBound);

        final Set<RatingChange> ratingChangeSet = new HashSet<>();

        matchesByDiscipline.forEach((discipline, disciplineMatches) -> {
            final RatingChange ratingChange = mapToRatingChange(discipline, disciplineMatches, username);
            ratingChangeSet.add(ratingChange);
        });

        return ratingChangeSet;
    }

    private List<Match> getAllMatchesForDefinedInterval(String username, LocalDate lowerBound, LocalDate upperBound) {
        final List<Match> matches = new ArrayList<>();

        if (rangeIsContainedWithinASingleMonth(lowerBound, upperBound)) {
            matches.addAll(matchArchiveService.getAllMatchesForMonthAndYear(username, lowerBound));
        } else {
            long monthsBetween = ChronoUnit.MONTHS.between(lowerBound, upperBound);

            for (int i = 0; i <= monthsBetween; i++) {
                final LocalDate newDateToQuery = lowerBound.plusMonths(i);
                matches.addAll(matchArchiveService.getAllMatchesForMonthAndYear(username, newDateToQuery));
            }
            Collections.sort(matches);
        }

        return matches;
    }

    private Map<String, List<Match>> filterMatchesOutsideTimeRangeAndGroupByDiscipline(List<Match> matches, LocalDate lowerBound, LocalDate upperBound){
        final Instant lowerBoundInstant = lowerBound.atStartOfDay(timezoneService.getZoneId()).toInstant();
        final Instant upperBoundInstant = upperBound.atStartOfDay(timezoneService.getZoneId()).toInstant();

        return matches.stream()
                .filter(match -> doesMatchLayWithinSpecifiedRange(match, lowerBoundInstant, upperBoundInstant))
                .collect(Collectors.groupingBy(Match::getTime_class));
    }

    private boolean rangeIsContainedWithinASingleMonth(LocalDate lower, LocalDate upper) {
        return lower.getMonthValue() == upper.getMonthValue() && lower.getYear() == upper.getYear();
    }


    private boolean doesMatchLayWithinSpecifiedRange(Match match, Instant lower, Instant upper) {
        return lower.compareTo(match.getEnd_time()) <= 0 && upper.compareTo(match.getEnd_time()) >= 0;
    }

    private RatingChange mapToRatingChange(String discipline, List<Match> disciplineMatches, String username) {
        final RatingChange ratingChange = new RatingChange();
        ratingChange.setDiscipline(Discipline.valueOf(discipline.toUpperCase()));

        final List<Point> pointChangesAcrossGames = disciplineMatches
                .stream()
                .map(match -> findColourPlayerAndReturnPoint(match, username))
                .toList();

        ratingChange.setPoints(pointChangesAcrossGames);
        return ratingChange;
    }

    private Point findColourPlayerAndReturnPoint(Match match, String username) {
        if (match.getBlack().getUsername().equalsIgnoreCase(username)) {
            return new Point(match.getBlack().getRating(), LocalDate.ofInstant(match.getEnd_time(), timezoneService.getZoneId()));
        }
        return new Point(match.getWhite().getRating(), LocalDate.ofInstant(match.getEnd_time(), timezoneService.getZoneId()));
    }
}
