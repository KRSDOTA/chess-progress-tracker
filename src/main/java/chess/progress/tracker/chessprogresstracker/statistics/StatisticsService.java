package chess.progress.tracker.chessprogresstracker.statistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatisticsService {

    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    public StatisticsService(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public String getStatistics(String username){
        final String url = "/player/" + username + "/stats";
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

}
