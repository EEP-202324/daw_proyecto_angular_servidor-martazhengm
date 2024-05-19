package com.gestion.alumnos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.alumnos.excepciones.ResourceNotFoundException;
import com.gestion.alumnos.modelo.Alumno;
import com.gestion.alumnos.repositorio.AlumnoRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class AlumnoControlador {

	@Autowired
	private AlumnoRepositorio repositorio;

	@GetMapping("/alumnos")
	public List<Alumno> listarTodosLosAlumnos() {
		return repositorio.findAll();
	}
	
	@PostMapping("/alumnos")
	public Alumno guardarAlumno(@RequestBody Alumno alumno) {
		return repositorio.save(alumno);
	}
    
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Long id){
			Alumno alumno = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno con el ID : " + id));
			return ResponseEntity.ok(alumno);
	}
	
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id,@RequestBody Alumno detallesAlumno){
		Alumno alumno = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno con el ID : " + id));
		
		alumno.setNombre(detallesAlumno.getNombre());
		alumno.setApellido(detallesAlumno.getApellido());
		alumno.setEmail(detallesAlumno.getEmail());
		
		Alumno alumnoActualizado = repositorio.save(alumno);
		return ResponseEntity.ok(alumnoActualizado);
    }
	
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarAlumno(@PathVariable Long id){
		Alumno alumno = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno con el ID : " + id));
		
		repositorio.delete(alumno);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}














