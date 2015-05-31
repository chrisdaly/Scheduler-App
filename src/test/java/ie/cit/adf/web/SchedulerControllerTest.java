package ie.cit.adf.web;

import static org.junit.Assert.*;
import ie.cit.adf.dao.SchedulerRepository;
import ie.cit.adf.domain.TaskObject;
import ie.cit.adf.service.SchedulerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

public class SchedulerControllerTest {

	private SchedulerService service;
	private SchedulerController tested;

/*	@Before
	public void setup() {
		service = Mockito.mock(SchedulerService.class);
		tested = new SchedulerController(service);
	}

	@Test
	public void testGetAll() {
		// Dummy array workaround.
		// Checks if the method actually ran - default is false.
		// Array is final, not the elements within it.
		final boolean[] methodInvoked = new boolean[1];

		// Create test implementation of repository.
		SchedulerRepository repo = new SchedulerRepository() {

			public void update(TaskObject task) {
			}

			public void insert(TaskObject task) {
			}

			public List<TaskObject> getAllTasks() {
				methodInvoked[0] = true;

				return new ArrayList<TaskObject>();
			}

			public TaskObject findById(String id) {

				return null;
			}

			public void delete(String id) {

			}
		};

		// Instantiate controller.
		SchedulerController tested = new SchedulerController(repo);

		// Check that the getAll method returns something.
		List<TaskObject> all = tested.getAllTaskItems();
		assertThat(all, notNullValue());
		assertThat(methodInvoked[0], equalTo(true));

	}

	@Test
	public void testCreate() {

		// Dummy array workaround.
		final TaskObject[] tasks = new TaskObject[1];

		// Create test implementation of repository.
		SchedulerRepository repo = new SchedulerRepository() {

			public void update(TaskObject task) {
			}

			public void insert(TaskObject task) {
				tasks[0] = task;
			}

			public List<TaskObject> getAllTasks() {
				return new ArrayList<TaskObject>();
			}

			public TaskObject findById(String id) {

				return null;
			}

			public void delete(String id) {

			}
		};

		// Instantiate controller.
		SchedulerController tested = new SchedulerController(repo);
		tested.create("some task");
		assertThat(tasks[0], notNullValue());
		assertThat(tasks[0].getText(), equalTo("some task"));*/
	}

	@Test
	public void testGetAllMock() {
		// Check that the getAll method returns something.
		List<TaskObject> all = tested.getAllTaskItems();
		assertThat(all, notNullValue());

		// Verify that the method actually gets called.
		Mockito.verify(repo, Mockito.times(1)).getAllTasks();
	}

	@Test
	public void testCreateMock() {
		tested.create("some task");

		Mockito.verify(repo).insert(
				Mockito.argThat(new ArgumentMatcher<TaskObject>() {

					@Override
					public boolean matches(Object argument) {
						return ((TaskObject) argument).getText().equals(
								"some task");
					}

					@Override
					public void describeTo(Description description) {
						description.appendText("expected: some task");
					}
				}));

	}
}