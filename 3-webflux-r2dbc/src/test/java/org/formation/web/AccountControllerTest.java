package org.formation.web;

import org.formation.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class AccountControllerTest {

    WebTestClient webTestClient;


    @Autowired
    AccountController accountController;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(accountController).build();
    }

    @Test
    void testFindAll() {

        webTestClient.get().uri("/accounts")
                .exchange()
                .expectBodyList(Account.class)
                .hasSize(2);
    }
}
