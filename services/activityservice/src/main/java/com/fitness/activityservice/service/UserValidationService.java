package com.fitness.activityservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@AllArgsConstructor
@Slf4j
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId) {
        log.info("Calling User Service for {}", userId);
        try {
            return userServiceWebClient.get()
                    .uri("/api/users/{usreId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
