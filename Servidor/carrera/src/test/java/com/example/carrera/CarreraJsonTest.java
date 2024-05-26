package com.example.carrera;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class CarreraJsonTest {
	@Autowired
    private JacksonTester<Carrera> json;

    @Test
    void cashCardSerializationTest() throws IOException {
        Carrera carrera = new Carrera(1L, "Psicología", "Ciencias de la Salud", "4 años", "6120€");
        assertThat(json.write(carrera)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(carrera)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(carrera)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(carrera)).hasJsonPathStringValue("@.nombre");
        assertThat(json.write(carrera)).extractingJsonPathStringValue("@.nombre")
             .isEqualTo("Psicología");
        assertThat(json.write(carrera)).hasJsonPathStringValue("@.rama");
        assertThat(json.write(carrera)).extractingJsonPathStringValue("@.rama")
             .isEqualTo("Ciencias de la Salud");
        assertThat(json.write(carrera)).hasJsonPathStringValue("@.duracion");
        assertThat(json.write(carrera)).extractingJsonPathStringValue("@.duracion")
             .isEqualTo("4 años");
        assertThat(json.write(carrera)).hasJsonPathStringValue("@.precio");
        assertThat(json.write(carrera)).extractingJsonPathStringValue("@.precio")
             .isEqualTo("6120€");
    }
    
    @Test
    void carreraDeserializationTest() throws IOException {
       String expected = """
       		{
				"id":1,
				"nombre": "Psicología",
				"rama": "Ciencias de la Salud",
				"duracion": "4 años",
				"precio": "6120€"
       		}
				             """;
       assertThat(json.parse(expected))
               .isEqualTo(new Carrera(1L, "Psicología", "Ciencias de la Salud", "4 años", "6120€"));
       assertThat(json.parseObject(expected).getId()).isEqualTo(1);
       assertThat(json.parseObject(expected).getNombre()).isEqualTo("Psicología");
       assertThat(json.parseObject(expected).getRama()).isEqualTo("Ciencias de la Salud");
       assertThat(json.parseObject(expected).getDuracion()).isEqualTo("4 años");
       assertThat(json.parseObject(expected).getPrecio()).isEqualTo("6120€");
       
    }
}
	

