

package wolftasks.src.tasks;
/**
 * This class represents the Active Tasks in the system. It has the same functionality of a TaskList, but requires that the Tasks that are incorporated in the ActiveTaskList are active Tasks.
 * Extends the AbstractTaskList since it will need the same basic functionality provided in AbstractTaskList.
 * @author Tyler Strickland
 *
 */
public class ActiveTaskList extends AbstractTaskList {

	/** Final name for the ActiveTaskList */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * Constructs a new ActiveTaskList by calling the AbstractTaskList constructor with the final name constant and 0 completed Tasks
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}
	
	/**
	 * Adds a Task to the ActiveTaskList if the Task is active
	 * @param t Task to add to the ActiveTaskList
	 * @throws IllegalArgumentException if the Task is not active
	 */
	@Override
	public void addTask(Task t) {
		if (t.isActive()) {
			super.addTask(t);
		}
		else {
			throw new IllegalArgumentException("Cannot add task to Active Tasks");
		}
	}
	
	/**
	 * Sets the name of the TaskList if the parameter matches the final constant name
	 * @param taskListName to set for the ActiveTaskList
	 * @throws IllegalArgumentException if the name parameter does not match Active Tasks
	 */
	@Override
	public void setTaskListName(String taskListName) {
		if (!taskListName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		super.setTaskListName(taskListName);
	}
	
	/**
	 * Creates a 2D string array of the Tasks in the ActiveTaskList. One column is the name of the Task, and the other is the name of the first TaskList in the Task's list of TaskLists
	 * @return 2D String array of the Tasks in ActiveTaskList
	 */
	public String[][] getTasksAsArray() {
		int size = getTasks().size();
		String[][] arr = new String[getTasks().size()][2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					arr[i][j] = getTasks().get(i).getTaskListName();
				}
				else {
					arr[i][j] = getTasks().get(i).getTaskName();
				}
			}
		}
		return arr;
	}
	
	/**
	 * Clears the ActiveTaskList of all Tasks
	 */
	public void clearTasks() {
		int size = getTasks().size();
		for (int i = 0; i < size; i++) {
			removeTask(0);
		}
		
	}
}
