package ie.cit.adf.web;

import static org.junit.Assert.*;
import ie.cit.adf.dao.TodoRepository;
import ie.cit.adf.domain.Todo;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TodoControllerTest {

	@Test
	public void testGetAll() {
		final boolean[] methodInvoked = new boolean[1];
		TodoRepository repo = new TodoRepository() {

			public void insert(Todo todo) {
				// TODO Auto-generated method stub
				
			}
			
			public List<Todo> getAll() {
				// TODO Auto-generated method stub
				methodInvoked[0] = true;
				return new ArrayList<Todo>();
			}
		};
		
		TodoController tested = new TodoController(repo);
		
		List<Todo> all = tested.getAllTodoItems();
		assertThat(all, notNullValue());
		assertThat(methodInvoked[0], equalTo(true));
	}
	
}