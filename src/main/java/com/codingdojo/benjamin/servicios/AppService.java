package com.codingdojo.benjamin.servicios;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.benjamin.modelos.Project;
import com.codingdojo.benjamin.modelos.Task;
import com.codingdojo.benjamin.modelos.User;
import com.codingdojo.benjamin.repositorios.RepositorioProyectos;
import com.codingdojo.benjamin.repositorios.RepositorioTareas;
import com.codingdojo.benjamin.repositorios.RepositorioUsuarios;

@Service
public class AppService {

	@Autowired
	private RepositorioUsuarios repositorio_usuarios;
	
	@Autowired
	private RepositorioProyectos repositorio_proyectos;
	
	@Autowired
	private RepositorioTareas repositorio_tareas;
	
	
	//REGISTRO
	public User register(User nuevoUsuario, BindingResult result) {
		
		String nuevoEmail = nuevoUsuario.getEmail();
		User existeUsuario = repositorio_usuarios.findByEmail(nuevoEmail);
		if(existeUsuario!=null) {
			result.rejectValue("email", "Unique", "El correo ya esta registrado en nuestra base de datos");
		}
		if (! nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Las contraseñas no coinciden");
		}
		if(!result.hasErrors()) {
			String contra_encr = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encr);
			return repositorio_usuarios.save(nuevoUsuario);
		}else {
			return null;
		}

	}
	
	//LOGIN
	public User login(String email, String password) {
		
		User existUsuario = repositorio_usuarios.findByEmail(email);
		if(existUsuario == null) {
			return null;
		}
		if(BCrypt.checkpw(password, existUsuario.getPassword())) {
			return existUsuario;
		}else {
			return null;
		}
		
	}
	
	public Project save_project(Project nuevoProyecto) {
		return repositorio_proyectos.save(nuevoProyecto);
	}
	
	public User find_user(Long id) {
		return repositorio_usuarios.findById(id).orElse(null);
	}
	public Project proyecto(Long id) {
		return repositorio_proyectos.findById(id).orElse(null);
	}
	
	public User save_user(User nuevoUsuario) {
		return repositorio_usuarios.save(nuevoUsuario);
	}
	public List<Project> find_project(User user){
		return repositorio_proyectos.findByColaboradoresNotContains(user);
	}
	public List<Project> project_joined(User colaborador){
		return repositorio_proyectos.findAllByColaboradores(colaborador);
	}
	
	
	public void join_project(Long user_id, Long project_id) {
		User myUser = find_user(user_id);
		Project proyecto = proyecto(project_id);
		
		myUser.getProjects_joined().add(proyecto);
		repositorio_usuarios.save(myUser);
	}
	public void remove_project(Long user_id, Long project_id) {
		User myUser = find_user(user_id);
		Project proyecto = proyecto(project_id);
		
		myUser.getProjects_joined().remove(proyecto);
		repositorio_usuarios.save(myUser);
	}
	public void delete(Long project_id) {
		repositorio_proyectos.deleteById(project_id);
	}
	
	public Task save_task(Task task) {
		return repositorio_tareas.save(task);
	}
	
	
}
