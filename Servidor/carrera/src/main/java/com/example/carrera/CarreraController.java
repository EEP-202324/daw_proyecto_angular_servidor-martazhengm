package com.example.carrera;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carreras")
class CarreraController {
@GetMapping("/{requestedId}")
	private ResponseEntity<Carrera> findById(
			@PathVariable Long requestedId) {
	if (requestedId.equals(99L)) {
        Carrera carrera = new Carrera(1L, "Psicologia", "Ciencias de la Salud", "4 años", "6120€");
        return ResponseEntity.ok(carrera);
    } else {
        return ResponseEntity.notFound().build();
    }
	}

}
