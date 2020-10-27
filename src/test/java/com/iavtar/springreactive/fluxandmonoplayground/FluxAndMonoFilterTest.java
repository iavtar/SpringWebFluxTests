package com.iavtar.springreactive.fluxandmonoplayground;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoFilterTest {

	List<String> names = Arrays.asList("Indra", "Adam", "Jenny", "Anna");
	
	@Test
	public void filterTest() {
		Flux<String> namesFlux = Flux.fromIterable(names)
				.filter(s -> s.startsWith("A"))
				.log();
		
		StepVerifier.create(namesFlux)
			.expectNext("Adam", "Anna")
			.verifyComplete();
		
	}
	
}
