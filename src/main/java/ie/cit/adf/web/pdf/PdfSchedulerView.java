package ie.cit.adf.web.pdf;

import ie.cit.adf.domain.TaskObject;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

// Corresponds to name that controller gives for all.
@Component("todo")
public class PdfSchedulerView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Table table = new Table(2);
		table.addCell("Text");
		table.addCell("Status");

		List<TaskObject> todos = (List<TaskObject>) model.get("todos");
		for (TaskObject todo : todos) {
			table.addCell(todo.getText());
			table.addCell(todo.isDone() ? "completed" : "open");

		}
		document.add(new Paragraph("List of Scheduler items:"));
		document.add(table);
	}
}