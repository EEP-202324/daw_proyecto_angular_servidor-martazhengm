package com.example.carrera;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class CarreraApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnACarreraWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/carreras/1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);

		String nombre = documentContext.read("$.nombre");
		assertThat(nombre).isEqualTo("Psicología");

		String rama = documentContext.read("$.rama");
		assertThat(rama).isEqualTo("Ciencias de la Salud");

		String duracion = documentContext.read("$.duracion");
		assertThat(duracion).isEqualTo("4 años");

		String precio = documentContext.read("$.precio");
		assertThat(precio).isEqualTo("6120€");
	}

	@Test
	void shouldNotReturnACarreraWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/carreras/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	//@DirtiesContext
	void shouldCreateNewCarrera() {
		Carrera newCarrera = new Carrera(2L, "ADE", "Ciencias Sociales y Jurídicas", "4 años", "6120€");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/carreras", newCarrera, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewCarrera = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCarrera, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		String nombre = documentContext.read("$.nombre");
		String rama = documentContext.read("$.rama");
		String duracion = documentContext.read("$.duracion");
		String precio = documentContext.read("$.precio");

		assertThat(id).isNotNull();
		assertThat(nombre).isEqualTo("ADE");
		assertThat(rama).isEqualTo("Ciencias Sociales y Jurídicas");
		assertThat(duracion).isEqualTo("4 años");
		assertThat(precio).isEqualTo("6120€");
	}
}
