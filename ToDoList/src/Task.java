
public class Task implements Comparable<Task> {
	
	public enum Category { PERSONAL, WORK, NONE }
	
	private String description;
	private Category category;
	private int priority;
	private boolean complete;

	public Task(String description) {
		this.description = description;
		category = Category.NONE;
		priority = 0;
		complete = false;

	}

	public Task(String description, int priority) {
		this.description = description;
		category = Category.NONE;
		this.priority = priority;
		complete = false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean equals(Task task) {
		if (this.description.equalsIgnoreCase(task.getDescription()) && this.category == task.getCategory()) {
			return true;
		} else {
			return false;
		}
	}

	public int compareTo(Task o) {
		if (this.complete == o.isComplete()) {
			if (this.priority == o.getPriority()) {
				return 0;
			} else if (this.priority < o.getPriority()) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.complete && !o.complete) {
			return -1;
		} else {
			return 1;
		}
	}

	public String toString() {
		if (complete) {
			return "[x] " + description + ", " + category + ", " + priority;
		} else {
			return "[ ] " + description + ", " + category + ", " + priority;
		}
	}

}
