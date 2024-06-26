package org.formation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	Main.methode1();
		
		Main.methode2();
		
		Main.methode3();*/
		
		Main.methode4().then(Main.methode5()).subscribe(e -> System.out.println(e));
		
		/* Main.methode5();
		
		Main.methode6();

		Main.methode7(); */
	}

	public static void methode1() {

		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(elements::add, e -> System.err.println(e),() -> System.out.println(elements));
	}
	
	public static void methode2() {
		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(new Subscriber<Integer>() {
			@Override
			public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(Integer integer) {
				elements.add(integer);
			}

			@Override
			public void onError(Throwable t) {	System.err.println(t); }

			@Override
			public void onComplete() {System.out.println(elements);	}
		});
	}
	
	public static void methode3() {
		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(new Subscriber<Integer>() {
			private Subscription s;
			int index = 0;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(2);
			}

			@Override
			public void onNext(Integer integer) {
				elements.add(integer);
				index++;
				if (index % 2 == 0) {
					s.request(2);
				}
			}

			@Override
			public void onError(Throwable t) {	System.err.println(t); }

			@Override
			public void onComplete() {System.out.println(elements);	}
		});
	}
	
	public static Mono<Integer> methode4() {

		return Flux.range(1, 10).map(i -> 3 * i)
												.filter(i -> i % 2 == 0)
												.flatMap(i -> Flux.just(i, -i))
												.log()
												.reduce(0,(x, y) -> x + y);


		
	}
	public static Mono<Integer> methode5() {
		return Flux.range(1, 10)
				.handle((value, sink) -> {
					var times3 = value*3;
					if (times3 % 2 == 0) {
						sink.next(times3);
					}
				}).flatMap(i -> Flux.just(i, -(Integer)i))
				.doOnNext(e -> System.out.println("From methode5 "+e))
				.reduce(0,(x, y) -> x + (Integer)y);

	}
	public static void methode6() {
		Flux.just(1, 2, 3, 4).map(i -> i * 2).zipWith(Flux.range(0, Integer.MAX_VALUE),
				(one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two)).log().subscribe();
	}
	
	public static void methode7() {
		Flux.range(1,5).delayElements(Duration.ofSeconds(1)).log().subscribe();

		try {
			Thread.sleep(5500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
