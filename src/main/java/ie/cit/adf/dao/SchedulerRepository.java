package ie.cit.adf.dao;

import ie.cit.adf.domain.TaskObject;

import java.util.List;

public interface SchedulerRepository {
	void insert(TaskObject task);
	
	void delete(String id);

	List<TaskObject> getAllTasks();

	TaskObject findById(String id);

	void update(TaskObject task);
}