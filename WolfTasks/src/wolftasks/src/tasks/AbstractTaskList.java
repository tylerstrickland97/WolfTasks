/**
 * 
 */
package wolftasks.src.tasks;

import wolftasks.src.util.ISwapList;
import wolftasks.src.util.SwapList;
import wolftasks.src.util.ISortedList;

/**
 * This class is the parent class of the TaskList hierarchy. It contains most of the common functionality of both the ActiveTaskList and the regular TaskList. It is capable of adding, removing and completing 
 * Tasks, while also getting its Tasks.
 * @author Tyler Strickland
 *
 */
public abstract class AbstractTaskList {

	/** Name of the TaskList*/
	private String taskListName;
	/** Number of Tasks from this TaskList that have been completed */
	private int completedCount;
	/** SwapList of tasks within the TaskList */
	private ISwapList<Task> tasks;
	
	/**
	 * Constructs a new TaskList and creates a new SwapList of Tasks
	 * @param taskListName name of the TaskList
	 * @param completedCount number of Tasks that have been completed in the TaskList
	 * @throws IllegalArgumentException if the number of completed tasks is less than 0.
	 */
	public AbstractTaskList(String taskListName, int completedCount) {
		setTaskListName(taskListName);
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		this.completedCount = completedCount;
		tasks = new SwapList<Task>();
		
	}
	
	/**
	 * Returns the name of the AbstractTaskList
	 * @return name of the AbstractTaskList
	 */
	public String getTaskListName() {
		return taskListName;
	}
	
	/**
	 * Sets the name of the AbstractTaskList
	 * @param taskListName name of the AbstractTaskList to set
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setTaskListName(String taskListName) {
		if (taskListName == null || "".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskListName = taskListName;
	}
	
	/**
	 * Returns the Tasks in the AbstractTaskList
	 * @return SwapList of Tasks in the AbstractTaskList
	 */
	public ISwapList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * Returns the number of completed Tasks for the AbstractTaskList
	 * @return number of completed Tasks for the AbstractTaskList
	 */
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
	 * Adds a Task to the AbstractTaskList and adds this instance of AbstractTaskList to the task being added.
	 * @param t Task to add to the AbstractTaskList
	 */
	public void addTask(Task t) {
		tasks.add(t);
		t.addTaskList(this);	
	}
	
	/**
	 * Removes the Task at the given index
	 * @param idx idx of the AbstractTaskList to remove from
	 * @return the Task that is removed. 
	 */
	public Task removeTask(int idx) {
		return tasks.remove(idx);
	}
	
	/**
	 * Gets the Task at the specified index
	 * @param idx idx of the AbstractTaskList to return
	 * @return Task at the specified index of the tasks in AbstractTaskList
	 */
	public Task getTask(int idx) {
		return tasks.get(idx);
	}
	
	/**
	 * Completes a Task by removing the Tasks from the list of Tasks in the AbstractTaskList. Increments completedCount
	 * @param task task to complete
	 */
	public void completeTask(Task task) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i) == task) {
				tasks.remove(i);
			}
		}
		completedCount++;
	}
	
	/**
	 * Abstract class for returning a 2D String array of the Tasks and the priority in the AbstractTaskList. Since the TaskList and ActiveTaskList will have different ways of completing this functionality, it is an abstract method
	 * @return 2D string array of the Tasks and priority in the AbstractTaskList
	 */
	public abstract String[][] getTasksAsArray();
	
	
	
	
	
}
