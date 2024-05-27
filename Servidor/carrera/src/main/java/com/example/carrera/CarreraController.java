package com.example.carrera;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

//	@GetMapping()
//	private ResponseEntity<Iterable<Carrera>> findAll() {
//	   return ResponseEntity.ok(carreraRepository.findAll());
//	}

	@GetMapping
	private ResponseEntity<List<Carrera>> findAll(Pageable pageable) {
		Page<Carrera> page = carreraRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre"))));
		return ResponseEntity.ok(page.getContent());
	}

	@PostMapping
	private ResponseEntity<Void> createCarrera(@RequestBody Carrera newCarreraRequest, UriComponentsBuilder ucb) {
		Carrera savedCarrera = carreraRepository.save(newCarreraRequest);
		URI locationOfNewCarrera = ucb.path("carreras/{id}").buildAndExpand(savedCarrera.getId()).toUri();
		return ResponseEntity.created(locationOfNewCarrera).build();
	}

	@PutMapping("/{requestedId}")
    public ResponseEntity<Void> putCarrera(@PathVariable Long requestedId, @RequestBody Carrera carreraActualizada) {
        Optional<Carrera> optional = carreraRepository.findById(requestedId);
        if (optional.isPresent()) {
            Carrera carrera = optional.get();
            Carrera updateCarrera = new Carrera (
                    carrera.getId(),
                    carreraActualizada.getNombre(),
                    carreraActualizada.getRama(),
                    carreraActualizada.getDuracion(),
                    carreraActualizada.getPrecio());
        carreraRepository.save(updateCarrera);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}