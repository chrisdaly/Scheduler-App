package ie.cit;

import java.util.List;

import ie.cit.adf.dao.SchedulerRepository;
import ie.cit.adf.domain.TaskObject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchedulerApplication {
// Bootstrap entry-point into application.
	
	public static void main(String[] args) {
		// Fetch web application context.
		// Static class to access context.
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"beans.xml");
		// Static class to access context.
		SchedulerRepository schedulerRepository = ctx.getBean(SchedulerRepository.class);

		// List out all the tasks in the DB.
		List<TaskObject> all = schedulerRepository.getAllTasks();
		System.out.println(all);

		// Create a new task object and insert it into the DB.
		TaskObject task = new TaskObject();
		task.setText("Close Windows");
		schedulerRepository.insert(task);
		
		((ClassPathXmlApplicationContext)ctx).close();
	}

}	