package com.codingdojo.benjamin.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.benjamin.modelos.Project;
import com.codingdojo.benjamin.modelos.User;
import com.codingdojo.benjamin.servicios.AppService;

@Controller
@RequestMapping("/projects")
public class ControladorProyectos {

	@Autowired
	private AppService servicio;
	
	@GetMapping("/new")
	public String new_project(@ModelAttribute("project") Project project, HttpSession session) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		return "newProject.jsp";
	}
	
	@PostMapping("/create")
	public String created_project(@Valid @ModelAttribute("project") Project project, BindingResult result,
									HttpSession session) {
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "newProject.jsp";
		}else {
			Project nuevoProyecto = servicio.save_project(project);
			
			User myUser= servicio.find_user(usuario_en_sesion.getId());
			myUser.getProjects_joined().add(nuevoProyecto);
			servicio.save_user(myUser);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/join/{project_id}")
	public String join(@PathVariable("project_id")  Long project_id, HttpSession session) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		servicio.join_project(usuario_en_sesion.getId(), project_id);
		return "redirect:/dashboard";
		
	}
	@GetMapping("/remove/{project_id}")
	public String remove(@PathVariable("project_id") Long project_id, HttpSession session) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		servicio.remove_project(usuario_en_sesion.getId(), project_id);
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/edit/{project_id}")
	public String edit(@PathVariable("project_id") Long project_id, HttpSession session, @ModelAttribute("actualiProject") Project nuevoProyecto, Model model) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		Project proyecto = servicio.proyecto(project_id);
		if(proyecto == null || !proyecto.getLead().getId().equals(usuario_en_sesion.getId())) {
			return "redirect:/dashboard";
		}
		model.addAttribute("projecto", proyecto);
		return "edit.jsp";
	}
	
	@PutMapping("/update")
	public String update(@Valid @ModelAttribute("actualiProject") Project project,  BindingResult result,HttpSession session) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			Project newProject = servicio.proyecto(project.getId());
			project.setColaboradores(newProject.getColaboradores());
			servicio.save_project(project);
			return "redirect:/dashboard";
		}
	}
	@GetMapping("/{project_id}")
	public String datails(@PathVariable("project_id") Long project_id, HttpSession session, Model model) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		
		User myUser= servicio.find_user(usuario_en_sesion.getId());
		model.addAttribute("user", myUser);
		Project proyecto = servicio.proyecto(project_id);
		model.addAttribute("proyecto", proyecto);
		
		return "details.jsp";
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete_project(@PathVariable("id") Long project_id, HttpSession session) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		Project proyecto = servicio.proyecto(project_id);
		if(proyecto == null || !proyecto.getLead().getId().equals(usuario_en_sesion.getId())) {
			return "redirect:/dashboard";
		}
		servicio.delete(project_id);
		return "redirect:/dashboard";
		
	}
	
	
	
	
	
	
}
