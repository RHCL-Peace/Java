import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lesson 18-19: Activity - Task Master GUI
 * 
 * This class represents the main TaskMaster JPanel. 
 * 
 * It contains a ToDoListPanel and the control sub-panel.
 * 
 * @author CS121 Instructors
 * @version [semester]
 * @author [your name]
 */
@SuppressWarnings("serial")
public class TaskMasterPanel extends JPanel
{	
	private JScrollPane toDoListScrollPane;
	private ToDoListPanel listPanel;
	private JTextField descriptionField;
	private JButton addTaskButton;
	
	/**
	 * Creates a new TaskMasterPanel.
	 */
	public TaskMasterPanel()
	{
		// ToDoList Panel
		setPreferredSize(new Dimension(500, 400));	
		setLayout(new BorderLayout());
		
		
		// ToDoList Panel
		listPanel = new ToDoListPanel("My ToDo List");
		listPanel.addTask(new Task("Task 1"));
		listPanel.addTask(new Task("Task 2"));
		listPanel.addTask(new Task("Task 3"));

		// JScrollPane
		toDoListScrollPane = new JScrollPane(listPanel);
		toDoListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		toDoListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(toDoListScrollPane, BorderLayout.CENTER);

		// Control Panel
		JPanel controlPanel = new JPanel();
		this.add(controlPanel, BorderLayout.SOUTH);
		
		// Add Task Text Field
		descriptionField = new JTextField(15);
		controlPanel.add(descriptionField);
		
		// Add Task Button
		addTaskButton = new JButton("Add Task");
		controlPanel.add(addTaskButton);
		addTaskButton.addActionListener(new AddTaskButtonListener());
		
		// Get Work Button
		JButton getWorkButton = new JButton("Get work");
		getWorkButton.addActionListener(new GetWorkButtonListener());
		controlPanel.add(getWorkButton);
		
	
		
	}
	
	private class AddTaskButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String description = descriptionField.getText();
			listPanel.addTask(new Task(description));
		}
		
	}
	
	
	private class GetWorkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			listPanel.showWorkGetDialog();
		}
		
	}

}
