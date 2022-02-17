/**
 * 
 */
package wolftasks.src.notebook;

import java.io.File;

import wolftasks.src.io.NotebookWriter;
import wolftasks.src.tasks.AbstractTaskList;
import wolftasks.src.tasks.ActiveTaskList;
import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;
import wolftasks.src.util.ISortedList;
import wolftasks.src.util.SortedList;



/**
 * This class represents a Notebook in the WolfTasks system. It contains most of the business logic in the system. It is capable of loading and writing to files. It is also to able to add, edit, and remove 
 * both Tasks and TaskLists.
 * @author Tyler Strickland
 *
 */
public class Notebook {
	/** Name of the notebook */
	private String notebookName;
	/** Flag that keeps track of whether the notebook has been changed or not */
	private boolean isChanged;
	/** List of TaskLists that are sorted alphabetically */
	private ISortedList<TaskList> taskLists;
	/** ActiveTaskList that represents the list of active Tasks in the Notebook */
	private ActiveTaskList activeTasks;
	/** The current TaskList that operations are being performed on */
	private AbstractTaskList currentTaskList;
	
	/**
	 * Constructs a new Notebook and initializes all of the lists. Sets isChanged to true and the currentTaskList to the ActiveTaskList
	 * @param notebookName name of the notebook
	 */
	public Notebook(String notebookName) {
		setNotebookName(notebookName);
		taskLists = new SortedList<TaskList>();
		activeTasks = new ActiveTaskList();
		isChanged = true;
		currentTaskList = activeTasks;
		
	}
	
	/**
	 * Saves the Notebook to a file 
	 * @param file file to save the notebook to 
	 * @throws IllegalArgumentException if there are issues saving the file
	 */
	public void saveNotebook(File file) {
		try {
			NotebookWriter.writeNotebookFile(file, notebookName, taskLists);
			isChanged = false;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
	
	/**
	 * Returns the name of the notebook
	 * @return name of the notebook
	 */
	public String getNotebookName() {
		return notebookName;
	}
	
	/**
	 * Sets the name of the notebook
	 * @param notebookName name of the notebook to set
	 * @throws IllegalArgumentException if the name is null, empty, or matches Active Tasks name
	 */
	private void setNotebookName(String notebookName) {
		if (notebookName == null || "".equals(notebookName) || ActiveTaskList.ACTIVE_TASKS_NAME.equals(notebookName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.notebookName = notebookName;
	}
	
	/**
	 * Returns the value of the isChanged variable
	 * @return isChanged variable
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Adds a TaskList to the taskLists
	 * @param t TaskList to add to the taskLists
	 * @throws IllegalArgumentException if the TaskList already exists in the taskLists or if the parameter matches the ActiveTaskList
	 */
	public void addTaskList(TaskList t) {
		if (t.getTaskListName().compareToIgnoreCase(ActiveTaskList.ACTIVE_TASKS_NAME) == 0) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < taskLists.size(); i++) {
			if (t.getTaskListName().compareToIgnoreCase(taskLists.get(i).getTaskListName()) == 0) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		taskLists.add(t);
		isChanged = true;
		currentTaskList = t;
	}
	
	/**
	 * Creates a string array of the TaskList names
	 * @return String array of the names of the TaskLists in the Notebook
	 */
	public String[] getTaskListsNames() {
		String[] arr = new String[taskLists.size() + 1];
		for (int i = 0; i < taskLists.size(); i++) {
			arr[i] = taskLists.get(i).getTaskListName();
		}
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = "Active Tasks";
		return arr;
		
	}
	
	/**
	 * Iterates through the taskList and finds the Active Tasks within those taskLists, and updates the activeTasks field to contain these active tasks
	 */
	private void getActiveTaskList() {
		int size;
		TaskList list = null;
		activeTasks.clearTasks();
		for (int i = 0; i < taskLists.size(); i++) {
			size = taskLists.get(i).getTasks().size();
			list = taskLists.get(i);
			for (int j = 0; j < size; j++) {
				if (list.getTasks().get(j).isActive()) {
					activeTasks.addTask(list.getTasks().get(j));
				}
			}
		}
	}
	
	/**
	 * Sets the currentTaskList to the specified TaskList. Helps us ensure we are performing operations on the correct TaskList
	 * @param name name of the TaskList to set to the currentTaskList
	 */
	public void setCurrentTaskList(String name) {
		if (name.equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			getActiveTaskList();
			currentTaskList = activeTasks;
			return;
		}
		boolean contains = false;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(name)) {
				contains = true;
				currentTaskList = taskLists.get(i);
			}
		}
		if (!contains) {
			getActiveTaskList();
			currentTaskList = activeTasks;
		}
	}
	
	/**
	 * Returns the currentTaskList
	 * @return currentTaskList field
	 */
	public AbstractTaskList getCurrentTaskList() {
		return currentTaskList;
	}
	
	/**
	 * Edits the currentTaskList by changing the name. It removes the the TaskList from the taskLists and then creates a new one with the correct fields, while also maintaining sorted order. 
	 * @param taskListName name to change the currentTaskList to
	 * @throws IllegalArgumentException if the parameter matches ActiveTasks, if the currentTaskList is the ActiveTaskList, if the name matches the currentTaskList, or if the name matches a TaskList in the list of TaskLists
	 */
	public void editTaskList(String taskListName) {
		if (currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		if (taskListName.compareToIgnoreCase(ActiveTaskList.ACTIVE_TASKS_NAME) == 0 || taskListName.compareToIgnoreCase(currentTaskList.getTaskListName()) == 0) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().compareToIgnoreCase(taskListName) == 0) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		TaskList list = null;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(currentTaskList.getTaskListName())) {
				list = new TaskList(taskListName, currentTaskList.getCompletedCount());
				for (int j = 0; j < taskLists.get(i).getTasks().size(); j++) {
					list.addTask(taskLists.get(i).getTasks().get(j));
				}
				taskLists.remove(i);
			}
		}
		taskLists.add(list);
		currentTaskList = list;
		isChanged = true;
		
	}
	
	/**
	 * Removes the currentTaskList from the taskLists and sets the currentTaskList to be the ActiveTaskList
	 */
	public void removeTaskList() {
		if (currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(currentTaskList.getTaskListName())) {
				taskLists.remove(i);
				currentTaskList = activeTasks;
				isChanged = true;
			}
		}
		
	
	}
	
	/**
	 * Adds a Task to the currentTaskList and updates isChanged
	 * @param task Task to add to the currentTaskList
	 */
	public void addTask(Task task) {
		if (!(currentTaskList instanceof TaskList)) {
			return;
		}
		else {
			try {
				currentTaskList.addTask(task);
				getActiveTaskList();
				isChanged = true;
			} catch (IllegalArgumentException e) {
				isChanged = false;
			}
		
		}
	}
	
	/**
	 * Edits the Task at the specified index in the currentTaskList. Changes all of the Tasks's fields to the ones specified in the parameter. Updates the ActiveTaskList and updates the isChanged field
	 * @param idx index of the currentTaskList to edit
	 * @param taskName new name for the Task
	 * @param taskDescription new description for the Task
	 * @param recurring whether the new Task is recurring or not
	 * @param active whether the Task is active or not
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if (!(currentTaskList instanceof TaskList)) {
			return;
		}
		else {
			currentTaskList.getTask(idx).setTaskName(taskName);
			currentTaskList.getTask(idx).setTaskDescription(taskDescription);
			currentTaskList.getTask(idx).setRecurring(recurring);
			currentTaskList.getTask(idx).setActive(active);
			getActiveTaskList();
			isChanged = true;
		}
	}
}
