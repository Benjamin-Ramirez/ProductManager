package com.codingdojo.benjamin.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.benjamin.modelos.Task;

@Repository
public interface RepositorioTareas extends CrudRepository<Task, Long>{

	List<Task> findAll();
	
}
