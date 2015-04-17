package ie.cit.adf.web;

import java.util.HashMap;
import java.util.List;

import ie.cit.adf.dao.ShedulerRepository;
import ie.cit.adf.domain.TaskObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SchedulerController {

	@Autowired
	private ShedulerRepository repo;
	
	
//	private SchedulerRepository repo;
//	public SchedulerController(ShedulerRepository repo) {
//		this.repo = repo;
//	}

	@RequestMapping("all")
	public String getAllTodoItems(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todo";
	};
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String create(@RequestParam String text) {
		// Automatically takes the parameter from the request and binds it to 
		// text value.
		
		System.out.println(text);
		
		TaskObject task = new TaskObject();
		task.setText(text);
		repo.insert(task);
		
		System.out.println(task);

		return "redirect:all";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		System.out.println(id);
		repo.delete(id);
		
		return "redirect:all";
	}
		


}