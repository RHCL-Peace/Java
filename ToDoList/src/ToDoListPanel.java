import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoListPanel extends JPanel{
	private ToDoList list;
	
	public ToDoListPanel(String name ) {
		list = new ToDoList(name);
		this.add(new JLabel(name));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void addTask(Task t) {
		list.addTask(t);
		this.add(new TaskButton(t));
		this.revalidate();
	}
	
	public void showWorkGetDialog() {
		Task nextTask = list.getWork();
		if (nextTask == null) {
			JOptionPane.showMessageDialog(null, "No work to do!", "GetWork", JOptionPane.PLAIN_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, nextTask.toString(), "No work to do", JOptionPane.PLAIN_MESSAGE);

		}
	}
	
}
