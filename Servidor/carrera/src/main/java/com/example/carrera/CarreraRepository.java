package com.example.carrera;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarreraRepository extends CrudRepository <Carrera, Long>, PagingAndSortingRepository<Carrera, Long>{

}
