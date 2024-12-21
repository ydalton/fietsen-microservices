package fact.it.tripservice.service;

import fact.it.tripservice.dto.BikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class BikeService {
    private final WebClient webClient;
    @Value("${bike-service.baseurl}")
    private String baseUrl;

    public BikeService() {
        this.webClient = WebClient.create();
    }

    public List<BikeResponse> getAll() {
        return Arrays.stream(Objects.requireNonNull(this.webClient.get()
                        .uri(this.baseUrl + "/api/bike")
                        .retrieve()
                        .bodyToMono(BikeResponse[].class)
                        .block()))
                        .toList();
    }

    public BikeResponse getById(Long id) {
        return this.webClient.get()
                .uri(this.baseUrl + String.format("/api/bike/%d", id))
                .retrieve()
                .bodyToMono(BikeResponse.class)
                .block();
    }
}
