package ie.cit.adf.service;

import ie.cit.adf.domain.TaskObject;

import java.util.List;

public interface SchedulerService {

	List<TaskObject> getAllTasks();

	TaskObject getTaskById(String id);

	TaskObject createNewTodoWithText(String text);

	void deleteTodo(String id);

	void invertTodo(String id);

	void updateTodoWithId(String id, TaskObject task);

}

