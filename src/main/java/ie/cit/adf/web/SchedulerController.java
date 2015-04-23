package ie.cit.adf.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.cit.adf.dao.SchedulerRepository;
import ie.cit.adf.domain.TaskObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriTemplate;

@Controller
public class SchedulerController {

	
	private SchedulerRepository repo;

	// Controller should enforce that a repository be made.
	@Autowired
	public SchedulerController(SchedulerRepository repo) {
		this.repo = repo;
	}

	// @RequestMappign automatically takes the parameter from the request and
	// binds it to
	// text value.
	@RequestMapping("all")
	public String getAllTodoItems(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todo";
	};

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String create(@RequestParam String text) {
		// Creates a new task by stripping the fields from URL.
		System.out.println(text);

		TaskObject task = new TaskObject();
		task.setText(text);
		repo.insert(task);

		System.out.println(task);

		return "redirect:all";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		// Deletes a task by id.
		System.out.println(id);
		repo.delete(id);

		return "redirect:all";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@PathVariable String id) {
		// Changes the status of a task.

		// Find specific task by id.
		TaskObject task = repo.findById(id);

		// Flip the task status.
		task.setDone(!task.isDone());
		repo.update(task);

		return "redirect:all";
	}

	// REST end-points
	// curl http://localhost:8080/scheduler-app/todo/
	@RequestMapping(value = { "all", "/" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<TaskObject> getAllTodoItems() {
		// Gets all the tasks.
		return repo.getAll();
	}

	/*
	 * $ curl -v -H "Accept: application/json"
	 * http://localhost:8080/scheduler-app/tod
	 * o/3dd9893e-91a3-4a76-b4d4-af9fc4dce967
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TaskObject getTask(@PathVariable String id) {
		// Gets a specific task by id.
		return repo.findById(id);
	}

	/*
	 * $ curl -v POST -H "Content-Type: application/json"
	 * http://localhost:8080/schedu ler-app/todo/ -d '{"text":"NEW TODO"}'
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody TaskObject task,
			HttpServletRequest request, HttpServletResponse response) {

		// Adds the task to the repository.
		repo.insert(task);

		// Returns the resource location.
		String location = getLocationForTodoResource(task, request);
		response.addHeader("Location", location);
	}

	/*
	 * $ curl -X DELETE
	 * localhost:8080/scheduler-app/todo/5d35e02c-c1b7-4ffd-9dd1-8ee4 069ef873
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTask(@PathVariable String id) {

		// Removes the task to the repository.
		repo.delete(id);
	}

	/*
	 * $ curl -v -X PUT -H "Content-Type application/json"
	 * http://localhost:8080/
	 * scheduler-app/todo/3dd9893e-91a3-4a76-b4d4-af9fc4dce967 -d '{"text":
	 * "NEWTODO(update)"}'
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = { "application/json" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable String id, @RequestBody TaskObject task) {
		// Search for the task by id in the repository.
		TaskObject taskStored = repo.findById(id);
		task.setId(taskStored.getId());

		// Flip the status of the task.
		repo.update(task);
	}

	// Helper methods
	private String getLocationForTodoResource(TaskObject task,
			HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(task.getId(), template).toASCIIString();
	}

	// Exception handler for findById if "Task" object doesn't exist in repo.
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}

}

// /