package org.formation;

import reactor.core.publisher.Sinks;

public class MainSink {
    public static void main(String[] args) {
        // Création d'un Sink
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        // Abonnement à un flux
        sink.asFlux().subscribe(System.out::println);

        // Émission d'éléments dans le flux
        sink.tryEmitNext("Element 1");
        sink.tryEmitNext("Element 2");

        // Émission d'une erreur dans le flux
        sink.tryEmitError(new RuntimeException("Something went wrong"));

        // Complétion du flux
        sink.tryEmitComplete();
    }
}
