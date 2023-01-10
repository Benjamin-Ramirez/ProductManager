package com.codingdojo.benjamin.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.benjamin.modelos.Project;
import com.codingdojo.benjamin.modelos.User;

@Repository
public interface RepositorioProyectos extends CrudRepository<Project, Long> {

	List<Project> findAll();
	
	List<Project> findByColaboradoresNotContains(User colaborador);
	
	List<Project> findAllByColaboradores(User colaborador);
	
}
