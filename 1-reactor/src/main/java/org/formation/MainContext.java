package org.formation;

import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


public class MainContext {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scheduler daemons = Schedulers.newParallel("Par",2,false);

		for ( int i= 0; i< 5; i++) {
			doPrefix(MainScheduler.methode1())
			.contextWrite(ctx -> ctx.put("uuid", ""+UUID.randomUUID()))
			.log()
			.subscribeOn(daemons)
			.subscribe();
		}
	}
	
	public static Flux<String> doPrefix(Flux<Integer> source) {
        return source
                .flatMap(data -> 
                	Mono.deferContextual(ctx -> Mono.just(ctx.getOrDefault("uuid",""))
                    .map(uid -> {
                    	return uid + " " + data;
                    }))
                );
	}
}
