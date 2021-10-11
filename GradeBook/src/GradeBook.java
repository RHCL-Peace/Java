import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lesson 13: Activity - GradeBook
 *
 * @author CS121 Instructors
 * @version [semester]
 * @author [your name]
 */
public class GradeBook {

	public static void main(String[] args) {
		
		
		ArrayList<Student> gradebook = new ArrayList<>();
		File file = new File("gradebook.csv");

		try {
			Scanner scan = new Scanner(file);

		
		while (scan.hasNextLine()) {
		String line = scan.nextLine();
			
		Scanner lineScan = new Scanner(line);
		lineScan.useDelimiter(",");

		String lastName = lineScan.next();
		String firstName = lineScan.next();
		int id = lineScan.nextInt();
		int grade = lineScan.nextInt();
			
		Student s = new Student(firstName, lastName, id);
		s.setGrade(grade);
		gradebook.add(s);
		
		lineScan.close();
		
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + file.getName() + " not found");
		}
		for  (Student s : gradebook) {
			System.out.println(s);
		}
	}

}
