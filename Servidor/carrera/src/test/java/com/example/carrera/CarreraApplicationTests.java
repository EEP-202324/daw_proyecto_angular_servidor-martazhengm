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
	@DirtiesContext
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

	@Test
	void shouldReturnAllCarrerasWhenListIsRequested() {
		ResponseEntity<String> response = restTemplate.getForEntity("/carreras", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int cashCardCount = documentContext.read("$.length()");
		assertThat(cashCardCount).isEqualTo(6);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);

		JSONArray nombres = documentContext.read("$..nombre");
		assertThat(nombres).containsExactlyInAnyOrder("Psicología", "ADE", "Derecho", "Derecho + ADE",
				"Máster acceso al Ejercicio de la Abogacía y la Procura",
				"Máster Oficial en Psicología General Sanitaria");

		JSONArray ramas = documentContext.read("$..rama");
		assertThat(ramas).containsExactlyInAnyOrder("Ciencias de la Salud", "Ciencias Sociales y Jurídicas",
				"Ciencias Sociales y Jurídicas", "Ciencias Sociales y Jurídicas", "Ciencias Sociales y Jurídicas",
				"Ciencias de la Salud");

		JSONArray duraciones = documentContext.read("$..duracion");
		assertThat(duraciones).containsExactlyInAnyOrder("4 años", "4 años", "4 años", "6 años", "18 meses", "2 años");

		JSONArray precios = documentContext.read("$..precio");
		assertThat(precios).containsExactlyInAnyOrder("6120€", "6120€", "6120€", "7038€", "7000€", "8969€");
	}

	@Test
	void shouldReturnAPageOfCarreras() {
		ResponseEntity<String> response = restTemplate.getForEntity("/carreras?page=0&size=1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray page = documentContext.read("$[*]");
		assertThat(page.size()).isEqualTo(1);
	}

	@Test
	void shouldReturnASortedPageOfCarrerass() {
		ResponseEntity<String> response = restTemplate.getForEntity("/carreras?page=0&size=1&sort=nombre,desc",
				String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray read = documentContext.read("$[*]");
		assertThat(read.size()).isEqualTo(1);

		String nombre = documentContext.read("$[0].nombre");
		assertThat(nombre).isEqualTo("Psicología");

		String rama = documentContext.read("$[0].rama");
		assertThat(rama).isEqualTo("Ciencias de la Salud");

		String duracion = documentContext.read("$[0].duracion");
		assertThat(duracion).isEqualTo("4 años");

		String precio = documentContext.read("$[0].precio");
		assertThat(precio).isEqualTo("6120€");
	}

	@Test
	void shouldReturnASortedPageOfCarrerasWithNoParametersAndUseDefaultValues() {
		ResponseEntity<String> response = restTemplate.getForEntity("/carreras", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray page = documentContext.read("$[*]");
		assertThat(page.size()).isEqualTo(6);

		JSONArray nombres = documentContext.read("$..nombre");
		assertThat(nombres).containsExactly("ADE", "Derecho", "Derecho + ADE",
				"Máster acceso al Ejercicio de la Abogacía y la Procura",
				"Máster Oficial en Psicología General Sanitaria", "Psicología");

	}
}