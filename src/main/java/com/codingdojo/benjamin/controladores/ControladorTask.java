package com.codingdojo.benjamin.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.benjamin.modelos.Project;
import com.codingdojo.benjamin.modelos.Task;
import com.codingdojo.benjamin.modelos.User;
import com.codingdojo.benjamin.servicios.AppService;

@Controller
public class ControladorTask {

	@Autowired
	private AppService servicio;
	
	
	@GetMapping("projects/{id}/tasks")
	public String task(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("task") Task task) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		Project proyecto = servicio.proyecto(id);
		model.addAttribute("proyecto", proyecto);
		return "task.jsp";
		
	}
	
	@PostMapping("/create/task")
	public String create_task(@Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session, Model model) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			model.addAttribute("proyecto", task.getProyecto());
			return "task.jsp";
		}
		servicio.save_task(task);
		return "redirect:/projects/"+task.getProyecto().getId()+"/tasks";
		
	}
	
	
	
	
	
	
	
	
	
	
}
