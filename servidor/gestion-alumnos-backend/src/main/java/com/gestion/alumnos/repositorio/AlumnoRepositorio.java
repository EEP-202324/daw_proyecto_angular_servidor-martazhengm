package com.gestion.alumnos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.alumnos.modelo.Alumno;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long>{

}
