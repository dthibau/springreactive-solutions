package org.formation.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
public class KafkaService {
    private FluxSink<String> kafkaSink;
    private Flux<String> kafkaFlux;

    public KafkaService() {
        kafkaFlux = Flux.create(emitter -> this.kafkaSink = emitter, FluxSink.OverflowStrategy.BUFFER);
    }

    @KafkaListener(topics = "sse", groupId = "sse_group")
    public void consume(String message) {
        if (kafkaSink != null) {
            kafkaSink.next(message);
        }
    }

    public Flux<String> getKafkaFlux() {
        return kafkaFlux;
    }
}
