/**
 * 
 */
package wolftasks.src.tasks;

/**
 * This class represents the basic TaskList. It is a child class of AbstractTaskList. It has the same functionality as provided in AbstractTaskList but is also able to compare TaskLists and also create its own 2D String array
 * @author Tyler Strickland
 *
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {
	
	/**
	 * Constructs a new TaskList by calling the AbstractTaskList constructor
	 * @param taskListName name of the TaskList
	 * @param completedCount number of Tasks that have been completed
	 */
	public TaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
	}
	
	/**
	 * Creates a 2D String array for the TaskList used for display. One column has the priority of the Task, and the other has the name of the Task. 
	 * @return 2D String array for the TaskList
	 */
	public String[][] getTasksAsArray() {
		String[][] arr = new String[getTasks().size()][2];
		for (int i = 0; i < getTasks().size(); i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					arr[i][j] = Integer.toString(i + 1);
				}
				else {
					arr[i][j] = getTasks().get(i).getTaskName();
				}
			}
		}
		return arr;
	}
	
	/**
	 * Compares the name of two TaskLists
	 * @param t TaskList to compare to the current instance of a TaskList
	 * @return int value for the difference between two TaskList names	
	 */
	public int compareTo(TaskList t) {
		return this.getTaskListName().compareTo(t.getTaskListName());
	}

}
