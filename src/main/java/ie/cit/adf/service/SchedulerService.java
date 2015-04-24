package ie.cit.adf.service;

import ie.cit.adf.domain.TaskObject;

import java.util.List;

public interface SchedulerService {

	List<TaskObject> getAllTasks();

	TaskObject getTaskById(String id);

	TaskObject createNewTaskWithText(String text);

	void deleteTask(String id);

	void invertTask(String id);

	void updateTaskWithId(String id, TaskObject task);

}

