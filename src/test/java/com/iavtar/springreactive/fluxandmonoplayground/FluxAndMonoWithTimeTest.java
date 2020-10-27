package com.iavtar.springreactive.fluxandmonoplayground;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoWithTimeTest {

	@Test
	public void infiniteSequence() throws InterruptedException {
		Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(200))
				.log();
		
		infiniteFlux.subscribe((element) -> System.out.println("Value is " + element));
		
		Thread.sleep(6000);
	}
	
	@Test
	public void infiniteSequenceTest() {
		Flux<Long> finiteFlux = Flux.interval(Duration.ofMillis(100))
				.take(3)
				.log();
		
		StepVerifier.create(finiteFlux)
		.expectSubscription()
		.expectNext(0L, 1L, 2L)
		.verifyComplete();
	}
	
	@Test
	public void infiniteSequenceMap() {
		Flux<Integer> finiteFlux = Flux.interval(Duration.ofMillis(100))
				.map(l -> new Integer(l.intValue()))
				.take(3)
				.log();
		
		StepVerifier.create(finiteFlux)
		.expectSubscription()
		.expectNext(0, 1, 2)
		.verifyComplete();
	}
	
	@Test
	public void infiniteSequenceMapWithDelay() {
		Flux<Integer> finiteFlux = Flux.interval(Duration.ofMillis(100))
				.delayElements(Duration.ofSeconds(1))
				.map(l -> new Integer(l.intValue()))
				.take(3)
				.log();
		
		StepVerifier.create(finiteFlux)
		.expectSubscription()
		.expectNext(0, 1, 2)
		.verifyComplete();
	}
	
}
