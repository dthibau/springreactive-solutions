package org.formation;

import java.net.URI;
import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveJavaClientWebSocket {

	public static void main(String[] args) throws InterruptedException {
		  
        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
                        URI.create("ws://localhost:8080/event-emitter"),
                        session -> {
                            // Receive messages and for each message, send a response
                            Flux<Void> receiveAndRespond = session.receive()
                                    .map(webSocketMessage -> webSocketMessage.getPayloadAsText())
                                    .flatMap(message -> {
                                        System.out.println("Received: " + message);
                                        return session.send(Mono.just(session.textMessage("response to: " + message)))
                                                .then();
                                    })
                                    .log();

                            return receiveAndRespond.then();
                        })
                .block(Duration.ofSeconds(60));
    }
}
