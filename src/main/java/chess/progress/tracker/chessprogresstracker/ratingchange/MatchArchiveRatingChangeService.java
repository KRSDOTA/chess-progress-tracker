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

        matchesByDiscipline.forEach((discipline, disciplineMatches) -> {
            final RatingChange ratingChange = mapToRatingChange(discipline, disciplineMatches, username);
            ratingChangeSet.add(ratingChange);
        });

        return ratingChangeSet;
    }

    private RatingChange mapToRatingChange(String discipline, List<Match> disciplineMatches, String username) {
        final RatingChange ratingChange = new RatingChange();
        ratingChange.setDiscipline(Discipline.valueOf(discipline.toUpperCase()));

        final List<Integer> pointChangesAcrossGames = disciplineMatches
                .stream()
                .map(match -> findColourPlayerAndReturnRating(match, username))
                .toList();

        ratingChange.setPoints(pointChangesAcrossGames);
        return ratingChange;
    }

    private Integer findColourPlayerAndReturnRating(Match match, String username) {
        if (match.getBlack().getUsername().equalsIgnoreCase(username)) {
            return match.getBlack().getRating();
        }
        return match.getWhite().getRating();
    }

    private boolean rangeIsContainedWithinASingleMonth(LocalDate lower, LocalDate upper) {
        return lower.getMonthValue() == upper.getMonthValue() && lower.getYear() == upper.getYear();
    }

    private boolean doesMatchLayWithinSpecifiedRange(Match match, Instant lower, Instant upper) {
        return lower.compareTo(match.getEnd_time()) <= 0 && upper.compareTo(match.getEnd_time()) >= 0;
    }
}
