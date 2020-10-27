package com.iavtar.springreactive.fluxandmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

//	@Test
//	public void fluxTest() {
//		Flux<String> stringFlux = Flux.just("Spring", "Springboot", "Reactive Spring")
////				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
//				.concatWith(Flux.just("After Error"))
//				.log();
//		stringFlux.subscribe(System.out::println, (e) -> System.err.println("Exception is " + e), () -> System.out.println("Completed"));
//	}
	
	@Test
	public void fluxTestElementsWithoutError() {
		Flux<String> stringFlux = Flux.just("Spring", "Springboot", "Reactive Spring").log();
		StepVerifier.create(stringFlux)
		.expectNext("Spring")
		.expectNext("Springboot")
		.expectNext("Reactive Spring")
		.verifyComplete();
	}
	
	@Test
	public void fluxTestElementsWithError() {
		Flux<String> stringFlux = Flux.just("Spring", "Springboot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.log();
		StepVerifier.create(stringFlux)
		.expectNext("Spring")
		.expectNext("Springboot")
		.expectNext("Reactive Spring")
//		.expectError(RuntimeException.class)
		.expectErrorMessage("Exception Occured")
		.verify();
	}
	
	@Test
	public void fluxTestElementsCount() {
		Flux<String> stringFlux = Flux.just("Spring", "Springboot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.log();
		StepVerifier.create(stringFlux)
		.expectNextCount(3)
		.expectErrorMessage("Exception Occured")
		.verify();
	}
	
	@Test
	public void fluxTestElementsWithError1() {
		Flux<String> stringFlux = Flux.just("Spring", "Springboot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.log();
		StepVerifier.create(stringFlux)
		.expectNext("Spring", "Springboot", "Reactive Spring")
//		.expectError(RuntimeException.class)
		.expectErrorMessage("Exception Occured")
		.verify();
	}
	
	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("Spring");
		StepVerifier.create(stringMono.log())
					.expectNext("Spring")
					.verifyComplete();
	}
	
	@Test
	public void monoTestWithError() {
		StepVerifier.create(Mono.error(new RuntimeException("Exception Occured")).log())
					.expectError(RuntimeException.class)
					.verify();
	}

}
