package ie.cit.adf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.adf.dao.SchedulerRepository;
import ie.cit.adf.domain.TaskObject;

@Component
//@Transactional
public class SchedulerServiceImpl implements SchedulerService {

	SchedulerRepository repo;

	@Autowired
	public SchedulerServiceImpl(SchedulerRepository repo) {
		this.repo = repo;
	}

	public List<TaskObject> getAllTasks() {
		return repo.getAllTasks();
	}

	public TaskObject getTaskById(String id) {
		return repo.findById(id);
	}

	public TaskObject createNewTaskWithText(String text) {
		TaskObject task = new TaskObject();
		task.setText(text);
		repo.insert(task);
		return task;
	}

	public void deleteTask(String id) {
		repo.delete(id);
	}

	public void invertTask(String id) {
		TaskObject task = repo.findById(id);
		task.setDone(!task.isDone());
		repo.update(task);
	}

	public void updateTaskWithId(String id, TaskObject task) {
		TaskObject tmpTask = repo.findById(id);
		tmpTask.setDone(task.isDone());
		tmpTask.setText(task.getText());
		repo.update(task);
	}

}
