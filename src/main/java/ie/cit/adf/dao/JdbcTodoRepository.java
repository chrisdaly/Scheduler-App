package ie.cit.adf.dao;

import ie.cit.adf.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTodoRepository implements TodoRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcTodoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Todo todo) {
		jdbcTemplate.update("insert into todo(id, text, done) values(?,?,?)",
				todo.getId(), todo.getText(), todo.isDone());

	}

	public List<Todo> getAll() {
		return jdbcTemplate.query("select id, text, done from todo",
				new TodoRowMapper());
	}
}

class TodoRowMapper implements RowMapper<Todo> {

	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("id");
		String text = rs.getString("text");
		boolean done = rs.getBoolean("done");
		Todo todo = new Todo();
		todo.setId(id);
		todo.setText(text);
		todo.setDone(done);
		return todo;
	}

}