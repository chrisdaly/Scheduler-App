package ie.cit.adf.dao;

import ie.cit.adf.domain.TaskObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcSchedulerRepository implements ShedulerRepository {
// Specific implementation of the interface

	private JdbcTemplate jdbcTemplate;

	@Autowired
	// Annotate controller to automatically inject datasource.
	public JdbcSchedulerRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<TaskObject> getAll() {
		// Map every row from the retrieved result set into a task object.
		return jdbcTemplate.query("SELECT id, text, done, tag FROM schedule",
				new TodoRowMapper());
	}
	
	public void insert(TaskObject task) {
		// Inserts a task object into the repository.
		jdbcTemplate.update("INSERT INTO schedule(id, text, done, tag) values(?,?,?,?)",
				task.getId(), task.getText(), task.isDone(), task.getTag());
	}
	
	public void delete(String id) {
		// Deletes a task object from the repository.
		jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
	}
	
	
	public TaskObject findByTag(String tag) {
		return jdbcTemplate.queryForObject("SELECT id, text, done, tag FROM schedule WHERE tag = ?",
				new TodoRowMapper(), tag);
	}


}

class TodoRowMapper implements RowMapper<TaskObject> {

	public TaskObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		// From result set extract the fields for one row at a time.
		String id = rs.getString("id");
		String text = rs.getString("text");
		boolean done = rs.getBoolean("done");
		String tag = rs.getString("tag");
		
		// Create a new task object based on the query results.
		TaskObject task = new TaskObject();
		task.setId(id);
		task.setText(text);
		task.setDone(done);
		task.setTag(tag);
		return task;
	}
}