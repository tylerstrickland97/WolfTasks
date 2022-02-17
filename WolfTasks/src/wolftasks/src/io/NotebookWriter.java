
   
/**
 * 
 */
package wolftasks.src.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;
import wolftasks.src.util.ISortedList;
import wolftasks.src.util.ISwapList;



/**
 * This class writes Notebooks to a file. This will be useful for allowing a user to save notebooks and then load them and edit them again. 
 * @author Tyler Strickland
 *
 */
public class NotebookWriter {

	/**
	 * Writes the Notebook to the specified file.
	 * @param file file to write the notebook to
	 * @param name name of the Notebook
	 * @param taskLists list of taskLists in the notebook
	 * @throws IllegalArgumentException if there are any issues writing to the file
	 */
	public static void writeNotebookFile(File file, String name, ISortedList<TaskList> taskLists) {
		try {
			PrintStream fileWriter = new PrintStream(file);
			ISwapList<Task> tasks = null;
			fileWriter.println("! " + name);
			for (int i = 0; i < taskLists.size(); i++) {
				fileWriter.println("# " + taskLists.get(i).getTaskListName() + "," + Integer.toString(taskLists.get(i).getCompletedCount()));
				tasks = taskLists.get(i).getTasks();
				for (int j = 0; j < tasks.size(); j++) {
					fileWriter.println(tasks.get(j).toString());
				}
			}
			fileWriter.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + file);
		}
	}
}
