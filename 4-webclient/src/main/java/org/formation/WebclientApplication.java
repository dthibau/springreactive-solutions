package org.formation;

import org.formation.service.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebclientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebclientApplication.class);
		// prevent SpringBoot from starting a web server
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RUN");

		WebClient client = WebClient.create("http://localhost:8080");

		User account = new User("David", 12.3);
		Mono<User> accountMono = Mono.just(account);

		// Start with a post
		Flux<User> accountsResponse = client.post().uri("/accounts").contentType(MediaType.APPLICATION_JSON)
				.body(accountMono, User.class).retrieve().bodyToFlux(User.class);

		account = accountsResponse.blockFirst();
		System.out.println("account  retereived "+account);

		Mono<User> accountResponse = client.get().uri("/accounts/{id}", account.getId()).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(User.class);

		accountResponse.subscribe(System.out::println);


		
		// Get all
		Flux<User> accounts = client.get().uri("/accounts").accept(MediaType.APPLICATION_JSON).exchange()
				.flatMapMany(r -> r.bodyToFlux(User.class));

		accounts = client.get().uri("/accounts").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);
		
		accounts.subscribe(a -> System.out.println(a));

		System.out.println("First account  retereived "+account);
		
		Thread.sleep(2000);

	}
}
