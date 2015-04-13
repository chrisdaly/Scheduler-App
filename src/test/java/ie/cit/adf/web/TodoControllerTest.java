package ie.cit.adf.web;

import static org.junit.Assert.*;
import ie.cit.adf.dao.ShedulerRepository;
import ie.cit.adf.domain.TaskObject;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TodoControllerTest {

	@Test
	public void testGetAll() {
		final boolean[] methodInvoked = new boolean[1];
		ShedulerRepository repo = new ShedulerRepository() {

			public void insert(TaskObject todo) {
				// TODO Auto-generated method stub
				
			}
			
			public List<TaskObject> getAll() {
				// TODO Auto-generated method stub
				methodInvoked[0] = true;
				return new ArrayList<TaskObject>();
			}
		};
		
		TodoController tested = new TodoController(repo);
		
		List<TaskObject> all = tested.getAllTodoItems();
		assertThat(all, notNullValue());
		assertThat(methodInvoked[0], equalTo(true));
	}
	
}
//