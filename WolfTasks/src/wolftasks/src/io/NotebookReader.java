/**
 * 
 */
package wolftasks.src.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import wolftasks.src.notebook.Notebook;
import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;

/**
 * Reads notebooks from a file. It creates tasks, places them into task lists, and then places those task lists into the notebook. This class is useful for loading notebooks after being saved. 
 * @author Tyler Strickland
 */
public class NotebookReader {

	/**
	 * Reads the notebook from the file and returns the new notebook
	 * @param file file to read the notebook from
	 * @return new notebook created
	 * @throws IllegalArgumentException if the file cannot be read for any reason
	 */
	public static Notebook readNodebookFile(File file) {
		String s = "";
		String name = "";
		Notebook n = null;
		try {
			Scanner fileScanner = new Scanner(file);
			while(fileScanner.hasNext()) {
				s += fileScanner.nextLine() + '\n';
			}
			fileScanner.close();
			Scanner stringScanner = new Scanner(s);
			if (stringScanner.next().charAt(0) != '!') {
				stringScanner.close();
				throw new IllegalArgumentException("Unable to load file.");
			}
			else {
				name = stringScanner.nextLine();
				name = name.substring(1);
				name = name.trim();
			}
			n = new Notebook(name);
			stringScanner.useDelimiter("\\r?\\n?[#]");
			while(stringScanner.hasNext()) {
				try {
					TaskList t = processTaskList(stringScanner.next());
					if (t != null) {
						n.addTaskList(t);
					}
				} catch (IllegalArgumentException e) {
					continue;
				}
				catch (NoSuchElementException e) {
					stringScanner.close();
					break;
				}
			}
			stringScanner.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		n.setCurrentTaskList("Active Tasks");
		return n;
		
	}
	
	/**
	 * Processes task lists given a line parameter. It returns a task list that will be added to the notebook
	 * @param line line to process for TaskLists
	 * @return TaskList to add to the notebook
	 */
	private static TaskList processTaskList(String line) {
		String name = "";
		int completedCount = 0;
		Scanner lineScanner = new Scanner(line);
		name = lineScanner.nextLine();
		name = name.substring(1);
		name = name.trim();
		TaskList list = null;
		Scanner nameScanner = new Scanner(name);
		nameScanner.useDelimiter(",");
		name = nameScanner.next();
		completedCount = nameScanner.nextInt();
		nameScanner.close();
		list = new TaskList(name, completedCount);

		lineScanner.useDelimiter("\\r?\\n?[*]");
		while(lineScanner.hasNext()) {
			try {
				Task t = processTask(lineScanner.next());
				if (t != null) {
					list.addTask(t);
				}
			} catch (IllegalArgumentException e) {
				//ignore
			}
		}
		lineScanner.close();
		
		return list;
	}
	
	/**
	 * Processes a task from the line parameter. It will return a Task object that is passed to the processTaskList method. The processTaskList method will add the Task to the TaskList it is creating and then return 
	 * all of the Tasks with the TaskList to the notebook
	 * @param line line to process for Tasks
	 * @return new Task object to pass to the processTaskList method
	 */
	private static Task processTask(String line) {
		Scanner fieldScanner = new Scanner(line);
		fieldScanner.useDelimiter("\n");
		String fieldLine = fieldScanner.next();
		String description = "";
		while (fieldScanner.hasNext()) {
			description += fieldScanner.next();
		}
		Scanner scan = new Scanner(fieldLine);
		scan.useDelimiter(",");
		String name = scan.next();
		name = name.trim(); 
		boolean recurring = false;
		boolean active = false;
		while (scan.hasNext()) {
			try {
				String s = scan.next();
				if ("recurring".equals(s)) {
					recurring = true;
				}
				else if ("active".equals(s)) {
					active = true;
				}
			} catch (NoSuchElementException e) {
				scan.close();
			}
		}
		scan.close();
		Task t = new Task(name, description, recurring, active);
		fieldScanner.close();
		return t;
	}
}
