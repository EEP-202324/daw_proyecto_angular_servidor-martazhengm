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
        Carrera carrera = new Carrera(99L, 123.45);
        assertThat(json.write(carrera)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(carrera)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(carrera)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(carrera)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(carrera)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(123.45);
    }
    
    @Test
    void carreraDeserializationTest() throws IOException {
       String expected = """
               {
                   "id":99,
                   "amount":123.45
               }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new Carrera(999L, 123.45));
       assertThat(json.parseObject(expected).id()).isEqualTo(1000);
       assertThat(json.parseObject(expected).amount()).isEqualTo(67.89);
    }
}
	

