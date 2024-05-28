package org.formation;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {


    @Bean
    public NewTopic sseTopic() {
        return new NewTopic("sse", 1, (short) 1);
    }
}
