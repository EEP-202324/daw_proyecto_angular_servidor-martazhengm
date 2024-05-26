package com.example.carrera;

import java.net.URI;
import java.util.Optional;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carreras")
class CarreraController {
	private final CarreraRepository carreraRepository;

	private CarreraController(CarreraRepository carreraRepository) {
		this.carreraRepository = carreraRepository;
	}

	@GetMapping("/{requestedId}")
	private ResponseEntity<Carrera> findById(@PathVariable Long requestedId) {
		Optional<Carrera> carreraOptional = carreraRepository.findById(requestedId);
		if (carreraOptional.isPresent()) {
			return ResponseEntity.ok(carreraOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	private ResponseEntity<Void> createCarrera(@RequestBody Carrera newCarreraRequest, UriComponentsBuilder ucb) {
		   Carrera savedCarrera= carreraRepository.save(newCarreraRequest);
		   URI locationOfNewCarrera= ucb
		            .path("carreras/{id}")
		            .buildAndExpand(savedCarrera.getId())
		            .toUri();
		   return ResponseEntity.created(locationOfNewCarrera).build();
	}

}
