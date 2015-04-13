package ie.cit.adf.domain;

import java.util.UUID;


public class TaskObject {
// Object representation (task) of repository table row.
	
	private String id;
	private String text;
	private boolean done;
	private String tag;

	public TaskObject() {
		// Each time a new object is instantiated it will be assigned a unique value.
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", text=" + text + ", done=" + done + ", tag=" + tag + "]";
	}
}
//////////