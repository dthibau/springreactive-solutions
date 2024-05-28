package org.formation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestServiceTest {

    @Autowired
    RestService restService;


    @Test
    void testLoadUser() {

        User user = User.builder().owner("owner").amount(1000.0d).build();
        String[] id = new String[1];
        StepVerifier.create(restService.createUser(user))
                .expectNextMatches(u -> {
                    assertThat(u).isNotNull();
                    assertThat(u.getId()).isNotNull();
                    id[0] = u.getId();
                    return true;
                })
                .verifyComplete();


        StepVerifier.create(restService.loadUser(id[0]))
                .expectNextMatches(u -> {
                    assertThat(u).isNotNull();
                    assertThat(u.getId()).isEqualTo(id[0]);
                    assertThat(u.getAmount()).isEqualTo(1000.0d);
                    return true;
                })
                .verifyComplete();

    }
}
