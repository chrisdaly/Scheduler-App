package ie.cit.adf.web;

import ie.cit.adf.dao.ShedulerRepository;
import ie.cit.adf.domain.TaskObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TimeServlet extends HttpServlet {

	private ShedulerRepository todoRepository;

	@Override
	public void init() throws ServletException {
		
		ServletContext servletContext = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		todoRepository = ctx.getBean(ShedulerRepository.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		PrintWriter writer = resp.getWriter();
		String msg = String
				.format("Current time is: %s", new Date().toString());
		writer.println(msg);
		writer.close();

		List<TaskObject> all = todoRepository.getAll();
		System.out.println(all);

	}
}