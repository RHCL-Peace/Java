import java.util.ArrayList;
import java.util.Collections;

public class ToDoList implements ToDoListInterface {
	private String name;
	private ArrayList<Task> tasks;

	private final String BORDER = "-------------\n";

	public ToDoList(String name) {
		this.name = name;
		tasks = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public void addTask(String description) {
		tasks.add(new Task(description));

	}
	
	public void addTask(String description, Task.Category category) {
		Task t = new Task(description);
		t.setCategory(category);
		tasks.add(t);
	}
	
	public Task getWork() {
		if (tasks.isEmpty()) {
			return null;
		} else {
			Task maxTask = Collections.max(tasks);
			if (maxTask.isComplete()) {
				return null;
			} else {
				return maxTask;
			}
		}
	}

	public ArrayList<Task> getTaskList() {
		ArrayList<Task> copy = new ArrayList<>();
		for ( Task t : tasks) {
			copy.add(t);
		}
		return copy;
	}

	public String toString() {
		String header = BORDER + name + BORDER;
		for (Task t : tasks) {
			header += (t + "\n");

		}
		return header;
	}

}
