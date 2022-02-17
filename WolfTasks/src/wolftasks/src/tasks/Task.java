package wolftasks.src.tasks;

import wolftasks.src.util.ISwapList;
import wolftasks.src.util.SwapList;

/**
 * 
 */




/**
 * This class represents a Task in the system. It is the most basic object in the system. It mostly contains getter and setter methods for its state, but it also is able to add TaskLists to 
 * the list of TaskLists and also mark itself as complete
 * @author Tyler Strickland
 *
 */
public class Task implements Cloneable {

	/** Name of the Task */
	private String taskName;
	/** Description for the Task */
	private String taskDescription;
	/** Boolean of whether the Task is recurring or not */
	private boolean recurring;
	/** Boolean of whether the Task is active or not */
	private boolean active;
	/** SwapList of TaskLists associated with the Task */
	private ISwapList<AbstractTaskList> taskLists;
	
	/**
	 * Constructs a new Task object and initializes the taskLists field
	 * @param taskName name of the Task
	 * @param taskDescription description for the Task
	 * @param recurring boolean of whether the Task is recurring or not
	 * @param active boolean of whether the Task is active or not
	 */
	public Task(String taskName, String taskDescription, boolean recurring, boolean active) {
		setTaskName(taskName);
		setTaskDescription(taskDescription);
		setRecurring(recurring);
		setActive(active);
		taskLists = new SwapList<AbstractTaskList>();
	}
	
	/**
	 * Returns the name of the Task
	 * @return name of the Task
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Sets the name of the Task
	 * @param taskName name of the Task to set
	 * @throws IllegalArgumentException if the parameter is null or an empty string
	 */
	public void setTaskName(String taskName) {
		if ("".equals(taskName) || taskName == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskName = taskName;
	}
	
	/**
	 * Returns the description of the Task
	 * @return description of the Task
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	
	/**
	 * Sets the description for the Task
	 * @param taskDescription description for the task to set
	 * @throws IllegalArgumentException if the taskDescription parameter is null
	 */
	public void setTaskDescription(String taskDescription) {
		if (taskDescription == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDescription = taskDescription;
	}
	
	/**
	 * Returns the recurring variable
	 * @return recurring variable
	 */
	public boolean isRecurring() {
		return recurring;
	}
	
	/**
	 * Sets the recurring variable
	 * @param recurring value of the recurring variable to set
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}
	
	/**
	 * Returns the active field
	 * @return active field
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * Sets the value for the active field
	 * @param active value to set for the active field
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/** 
	 * Returns the name of the TaskList at the front of the taskLists list
	 * @return name of the first TaskList in the taskLists field or an empty string if there are no TaskLists associated with the Task
	 */
	public String getTaskListName() {
		if (taskLists == null || taskLists.size() == 0) {
			return "";
		}
		return taskLists.get(0).getTaskListName();
	}
	
	/**
	 * Adds a TaskList to the Tasks list of TaskLists if the TaskList is not null and it doesn't match an existing TaskList in taskLists
	 * @param list list to add to the taskLists field
	 * @throws IllegalArgumentException if the parameter is null
	 */
	public void addTaskList(AbstractTaskList list) {
		if (list == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(list.getTaskListName())) {
				return;
			}
		}
		taskLists.add(list);
		
	}
	
	/**
	 * Completes a Task and removes it from each of the TaskLists associated with it. If it is recurring, it is cloned and added back to each of the TaskLists associated with it 
	 */
	public void completeTask() {
		for (int i = 0; i < taskLists.size(); i++) {
			taskLists.get(i).completeTask(this);
		}
		
		if (recurring) {
			try {
				Task t = (Task) clone();
				for (int i = 0; i < taskLists.size(); i++) {
					taskLists.get(i).addTask(t);
				}
			} catch (CloneNotSupportedException e) {
				return;
			}
		}
	}
	
	/**
	 * Creates a clone of the Task to add back to TaskLists if the Task is recurring
	 * @return clone of the Task
	 * @throws CloneNotSupportedException if there are no TaskLists associated with the Task
	 */
	public Object clone() throws CloneNotSupportedException {
		
		if (taskLists.size() == 0) {
			throw new CloneNotSupportedException("Cannot clone.");
		}
		else {
			Task clone = new Task(taskName, taskDescription, recurring, active);
			for (int i = 0; i < taskLists.size(); i++) {
				clone.addTaskList(taskLists.get(i));
			}
			return clone;
		}
	}
	
	/**
	 * Creates a String representation of the Task
	 * @return String representation for the Task with all of its information
	 */
	public String toString() {
		String s = "";
		if (recurring && active) {
			s = "* " + taskName + ",recurring,active" + '\n' + taskDescription;
			return s;
		}
		if (recurring && !active) {
			s = "* " + taskName + ",recurring" + '\n' + taskDescription;
			return s;
		}
		if (!recurring & active) {
			s = "* " + taskName + ",active" + '\n' + taskDescription;
			return s;
		}
		else {
			s = "* " + taskName + '\n' + taskDescription;
			return s;
		}
	}
	
	
	
}
