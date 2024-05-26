package com.example.carrera;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarreraApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnACarreraWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/carreras/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);
        
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Psicologia");
        
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
}
