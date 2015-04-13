package ie.cit.adf.dao;

import ie.cit.adf.domain.TaskObject;

import java.util.List;

public interface ShedulerRepository {
	void insert(TaskObject todo);

	List<TaskObject> getAll();
}