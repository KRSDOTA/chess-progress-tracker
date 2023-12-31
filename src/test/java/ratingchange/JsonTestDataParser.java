package ratingchange;

import chess.progress.tracker.chessprogresstracker.dtomodels.match.Match;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonTestDataParser {

    public static List<Match> parseMatchData(String filePath) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try (FileReader fileReader = new FileReader(filePath)) {
            return objectMapper.readValue(fileReader, new TypeReference<>(){});
        }
    }
}