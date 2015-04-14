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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SchedulerController {

	private ShedulerRepository repo;
	
	@Autowired
	public SchedulerController(ShedulerRepository repo) {
		this.repo = repo;
	}

//	@RequestMapping("all")
//	public ModelAndView getAllTodoItems() {
//		List<TaskObject> all = repo.getAll();
//		HashMap<String, Object> model = new HashMap<String, Object>();
//		// "tasks" must be matched in views.
//		model.put("todos", all);
//		return new ModelAndView("todo", model);
//	}
	
	@RequestMapping("all")
	public String getAllTodoItems(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todo";
	};
	
//	@RequestMapping(value = { "all", "/" }, method = RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public @ResponseBody List<Todo> getAllTodoItems() {
//		return repo.getAll();
//	}
	/////////
		

}