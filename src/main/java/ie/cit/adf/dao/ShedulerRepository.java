package ie.cit.adf.dao;

import ie.cit.adf.domain.TaskObject;

import java.util.List;

public interface ShedulerRepository {
	void insert(TaskObject task);
	
	void delete(String id);

	List<TaskObject> getAll();

	TaskObject findById(String id);

	void update(TaskObject task);
}