package ie.cit.adf.web;

import java.util.List;

import ie.cit.adf.dao.TodoRepository;
import ie.cit.adf.domain.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TodoController {

	private TodoRepository repo;
	
	@Autowired
	public TodoController(TodoRepository repo) {
		this.repo = repo;
	}

	@RequestMapping("all")
	public String getAllTodoItems(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todo";
	};
	
	@RequestMapping(value = { "all", "/" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Todo> getAllTodoItems() {
		return repo.getAll();
	}
	
		

}