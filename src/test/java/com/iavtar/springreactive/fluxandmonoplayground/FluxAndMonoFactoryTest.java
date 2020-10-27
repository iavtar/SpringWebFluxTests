package com.iavtar.springreactive.fluxandmonoplayground;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoFactoryTest {

	List<String> names = Arrays.asList("Indra", "Adam", "Jenny", "Anna");
	
	@Test
	public void fluxUsingIterable() {
		Flux<String> namesFlux = Flux.fromIterable(names).log();
		
		StepVerifier.create(namesFlux)
			.expectNext("Indra", "Adam", "Jenny", "Anna")
			.verifyComplete();
	}

	@Test
	public void fluxUsingArray() {
		String[] names = new String[] {"Indra", "Adam", "Jenny", "Anna"};
		Flux<String> namesFlux = Flux.fromArray(names).log();
		StepVerifier.create(namesFlux)
		.expectNext("Indra", "Adam", "Jenny", "Anna")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingstream() {
		Flux<String> namesFlux = Flux.fromStream(names.stream()).log();
		StepVerifier.create(namesFlux)
			.expectNext("Indra", "Adam", "Jenny", "Anna")
			.verifyComplete();
	}
	
	@Test
	public void monoUsingJustOrEmpty() {
		Mono<String> mono = Mono.justOrEmpty(null);
		StepVerifier.create(mono.log())
			.verifyComplete();
	}
	
	@Test
	public void monoUsingSupplier() {
		Supplier<String> stringSupplier = () -> "adam";
		
		Mono<String> stringMono = Mono.fromSupplier(stringSupplier);
		
		StepVerifier.create(stringMono.log())
			.expectNext("adam")
			.verifyComplete();
	}
	
	@Test
	public void fluxUsingRange() {
		Flux<Integer> integerFlux = Flux.range(1, 5);
		
		StepVerifier.create(integerFlux.log())
			.expectNext(1, 2, 3, 4, 5)
			.verifyComplete();
	}
	
}
