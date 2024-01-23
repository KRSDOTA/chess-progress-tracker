package chess.progress.tracker.chessprogresstracker.ratingchange;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Point {

  private Integer rating;

  private LocalDate time;

}
