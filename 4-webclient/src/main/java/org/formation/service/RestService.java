package org.formation.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RestService {

    WebClient client;

    public RestService(WebClient.Builder builder) {
        client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<User> loadUser(String id) {

        return client.get()
                .uri("/accounts/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(User.class);
    }

    public Mono<User> createUser(User user) {

        return client.post()
                .uri("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user),User.class)
                .retrieve().bodyToMono(User.class);
    }

    public Flux<User> allUser(long id) {

        return client.get()
                .uri("/accounts/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(User.class);
    }
}