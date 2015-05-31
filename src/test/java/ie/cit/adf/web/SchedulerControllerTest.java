package ie.cit.adf.web;

import ie.cit.adf.domain.TaskObject;
import ie.cit.adf.service.SchedulerService;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;

public class SchedulerControllerTest {

	private SchedulerService service;
	private SchedulerController tested;

	@Before
	public void setup() {
		service = Mockito.mock(SchedulerService.class);
		tested = new SchedulerController(service);
	}

	@Test
	public void testGetAll() {
		List<TaskObject> all = tested.getAllTaskItems();
		assertThat(all, notNullValue());
		verify(service).getAllTasks();
	}
	
	@Test
	public void testGetAllTasks() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		String view = tested.getAllTaskItems(model);
		assertThat(view, CoreMatchers.equalTo("scheduler"));
		assertThat(model.get("tasks"), notNullValue());
		verify(service).getAllTasks();
	}
	
	@Test
	public void testCreate() {
		tested.create("new Task");
		Mockito.verify(service).createNewTaskWithText(
				Mockito.argThat(new ArgumentMatcher<String>() {

					@Override
					public boolean matches(Object argument) {
						return ((String) argument).equals("new Task");
					}

					@Override
					public void describeTo(Description description) {
						description.appendText("expected: new Task");
					}
				}));
	}
	
	@Test
	public void testUpdate() {
		TaskObject task = tested.getTask("1");
		boolean status = task.isDone();

		tested.update("1");
		boolean status2 = task.isDone();
		
		assertEquals(status, equals(!status2));
	}
	
}