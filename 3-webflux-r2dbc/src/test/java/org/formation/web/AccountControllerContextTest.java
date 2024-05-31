package org.formation.web;

import org.formation.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class AccountControllerContextTest {


    WebTestClient webTestClient;

    @Autowired
    ApplicationContext context;


    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void testFindAll() {

        webTestClient.get().uri("/accounts")
                .exchange()
                .expectBodyList(Account.class)
                .hasSize(2);
    }
}
