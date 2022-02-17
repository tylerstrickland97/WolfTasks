
   
package wolftasks.test.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import wolftasks.src.io.NotebookWriter;
import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;
import wolftasks.src.util.ISortedList;
import wolftasks.src.util.SortedList;




/**
 * Tests the NotebookWriter class
 * @author Tyler Strickland
 *
 */
public class NotebookWriterTest {
	
	/**
	 * Tests constructing a new NotebookWriter
	 */
	@Test
	public void testNotebookWriter() {
		NotebookWriter w = new NotebookWriter();
		assertNotNull(w);
	}

	/** 
	 * Tests writing notebooks to files and ensuring they match pre-existing files
	 */
	@Test
	public void testWriteNotebookFile() {
		String name = "School";
		ISortedList<TaskList> taskLists = new SortedList<TaskList>();
		
		TaskList list1 = new TaskList("CSC 216", 35);
		TaskList list2 = new TaskList("CSC 226", 23);
		TaskList list3 = new TaskList("Habits", 0);
		
		Task task1 = new Task("Read Project 2 Requirements", "Read Project 2 requirements " + '\n' + "(https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)" + '\n'
				+ "and identify candidate classes and methods.", false, false);
		
		Task task2 = new Task("Create CRC Cards", "Identify the key classes and create CRC cards. Note" + '\n' + "responsibilities, collaborators, and possible state.", false, true);
		
		Task task3 = new Task("Transfer CRC Cards to UMLetino", "Start creating a UML class diagram from the requirements", false, false);
		
		Task task4 = new Task("Download design proposal and rational template", "See (https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)" + '\n'
				+ "for template link", false, false);
		
		
		Task task5 = new Task("Write design proposal and rationale", "Start with UML class diagram description. Incorporate feedback " + '\n'
				+ "from Project 1.", false, false);
	
		
		Task task6 = new Task("Identify 5 system tests", "Consider 5 major paths through the system when working with" + '\n'
				+ "notebooks, task lists, and tasks.  How would I use the system" + '\n'
				+ "to keep track of my tasks?", false, false);
		
		Task task7 = new Task("Watch lecture video", "Keep up with lecture videos each week", true, true);
		
		Task task8 = new Task("Complete exercises", "Complete exercises by Sunday at 11:45pm each week", true, false);
		
		Task task9 = new Task("Complete quizzes", "Weekly quizzes open Thursdays at 3pm and close Mondays at 11:30am" + '\n'
				+ "(all times Eastern)", true, false);
		list1.addTask(task1);
		list1.addTask(task2);
		list1.addTask(task3);
		list1.addTask(task4);
		list1.addTask(task5);
		list1.addTask(task6);
		list1.addTask(task7);
		list1.addTask(task8);
		list1.addTask(task9);
		
		Task task10 = new Task("Homework 7", "- Review the assignment" + '\n'
				+ "- Schedule time to work on the assignment" + '\n'
				+ "Don't forget to submit!", false, false);
		Task task11 = new Task("Homework 8", "- Review the assignment" + '\n'
				+ "- Schedule time to work on the assignment" + '\n'
				+ "Don't forget to submit!", false, false);
		Task task12 = new Task("Homework 9", "- Review the assignment" + '\n'
				+ "- Schedule time to work on the assignment" + '\n'
				+ "Don't forget to submit!", false, false);
		Task task13 = new Task("Homework 10", "- Review the assignment" + '\n'
				+ "- Schedule time to work on the assignment" + '\n'
				+ "Don't forget to submit!", false, false);
		Task task14 = new Task("Watch lectures", "Watch lectures associated with HW7 by March 31", true, true);
		list2.addTask(task10);
		list2.addTask(task11);
		list2.addTask(task12);
		list2.addTask(task13);
		list2.addTask(task14);
		
		Task task15 = new Task("Exercise", "Exercise every day. " + '\n'
				+ "Alternate between cardio and weight training", true, true);
		Task task16 = new Task("Floss", "Floss when brushing my teeth before bed! ", true, true);
		list3.addTask(task15);
		list3.addTask(task16);
		
		taskLists.add(list1);
		taskLists.add(list2);
		taskLists.add(list3);
		
		File file = new File("test-files/actual_notebook1.txt");
		try {
			NotebookWriter.writeNotebookFile(file, name, taskLists);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		checkFiles("test-files/notebook1.txt", "test-files/actual_notebook1.txt");
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
