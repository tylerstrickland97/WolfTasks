
   
package wolftasks.test.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import wolftasks.src.io.NotebookReader;
import wolftasks.src.notebook.Notebook;



/**
 * Tests the NotebookReader class
 * @author Tyler Strickland
 *
 */
public class NotebookReaderTest {


	/**
	 * Tests reading a Notebook file and constructing the correct Notebook
	 */
	@Test
	public void testReadNodebookFile() {
		File file = new File("test-files/notebook1.txt");
		File invalidFile = new File("test-files/notebook3.txt");
		File file2 = new File("test-files/notebook2.txt");
		File invalidFile2 = new File("test-files/notebook47.txt");
		Notebook notebook = null;
		NotebookReader r = new NotebookReader();
		assertNotNull(r);
		try {
			notebook = NotebookReader.readNodebookFile(invalidFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		try {
			notebook = NotebookReader.readNodebookFile(invalidFile2);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		
		try {
			notebook = NotebookReader.readNodebookFile(file);
			assertEquals("School", notebook.getNotebookName());
			assertEquals(4, notebook.getTaskListsNames().length);
			String[] arr = notebook.getTaskListsNames();
			assertEquals("Active Tasks", arr[0]);
			assertEquals("CSC 216", arr[1]);
			assertEquals("CSC 226", arr[2]);
			assertEquals("Habits", arr[3]);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}

		try {
			notebook = NotebookReader.readNodebookFile(file2);
			assertEquals("School", notebook.getNotebookName());
			assertEquals(4, notebook.getTaskListsNames().length);
			String[] arr = notebook.getTaskListsNames();
			assertEquals("Active Tasks", arr[0]);
			assertEquals("CSC 216", arr[1]);
			assertEquals("CSC 226", arr[2]);
			assertEquals("Habits", arr[3]);
			notebook.setCurrentTaskList("CSC 216");
			assertEquals(0, notebook.getCurrentTaskList().getTasks().size());
			notebook.setCurrentTaskList("CSC 226");
			assertEquals(5, notebook.getCurrentTaskList().getTasks().size());
			notebook.setCurrentTaskList("Habits");
			assertEquals(2, notebook.getCurrentTaskList().getTasks().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		File file3 = new File("test-files/notebook7.txt");
		try {
			notebook = NotebookReader.readNodebookFile(file3);
			assertEquals("Personal", notebook.getNotebookName());
			assertEquals(2, notebook.getTaskListsNames().length);
			String[] arr = notebook.getTaskListsNames();
			assertEquals("Active Tasks", arr[0]);
			assertEquals("Habits", arr[1]);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	
		
	}

}
