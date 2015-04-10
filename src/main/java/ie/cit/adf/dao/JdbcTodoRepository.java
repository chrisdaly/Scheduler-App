package ie.cit.adf.dao;

import ie.cit.adf.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcTodoRepository implements TodoRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTodoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Todo todo) {
		jdbcTemplate.update("INSERT INTO todo(id, text, done, tag) values(?,?,?,?)",
				todo.getId(), todo.getText(), todo.isDone(), todo.getTag());
	}
	
	public void delete(Todo todo) {
		jdbcTemplate.update("DELETE FROM todo WHERE id = (id) values(?)",
				todo.getId());
	}
	
	public Todo findByTag(String tag) {
		return jdbcTemplate.queryForObject("SELECT id, text, done, tag FROM todo WHERE tag = ?",
				new TodoRowMapper(), tag);
	}

	public List<Todo> getAll() {
		return jdbcTemplate.query("select id, text, done, tag from todo",
				new TodoRowMapper());
	}
}

class TodoRowMapper implements RowMapper<Todo> {

	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("id");
		String text = rs.getString("text");
		boolean done = rs.getBoolean("done");
		String tag = rs.getString("tag");
		Todo todo = new Todo();
		todo.setId(id);
		todo.setText(text);
		todo.setDone(done);
		todo.setText(tag);
		return todo;
	}
}