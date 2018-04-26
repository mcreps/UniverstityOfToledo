import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.connection.DatabaseManager;

public class runnable {

	private static Logger logger = LoggerFactory.getLogger(runnable.class);	
	
	public static void main(String[] args) {
					
		/* Make a database connections with properties files */
		DatabaseManager databaseManager = new DatabaseManager("database.properties");
		Connection connection = databaseManager.establishConnection();
		if (null == connection) {
			System.exit(1);
		}
		
		// Build all the required tables
		TableBuilds.buildTables(connection);
	
		System.out.printf("1. Object Example with Max\n");
		System.out.printf("2. Example 1\n");
		System.out.printf("3. Example 2\n");
		System.out.printf("4. Inclass 1\n");	
		System.out.printf("5. Inclass 2\n");	
		System.out.printf("6. Object With User Input\n");
		System.out.printf("7. Join Examples\n");
		System.out.printf("8. Advanced Topics\n");
		System.out.printf("9. Instructor Who Teaches MTH1010\n");
		System.out.printf("10. FooBar\n");
		System.out.printf("11. Simple\n");
		System.out.printf("12. UT_Student\n");
		System.out.printf("13. UT_Student Balances\n");
		
		/* Open scanner for std inout */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Assignment Name: ");	
		/* get the user input and store in name */
		String name = scanner.nextLine();		

		switch(name) {
		case "1":
			Object_Example.fetch(connection);
			break;
		case "2":
			logger.debug("Running Example 1");
			Example1 ex1 = new Example1();
			ex1.displayExample1(connection);
			break;
		case "3":
			logger.debug("Running Example 2");
			Example2 ex2 = new Example2();
			ex2.displayEquipmentType(connection);
			break;
		case "4":
			logger.debug("Running Inclass 1");

			InsertCourses insertCourses = new InsertCourses();
			insertCourses.inserts(connection);
			break;
		case "5":
			InsertInstructor ii = new InsertInstructor();
			ii.insertInstructor(connection);
			break;
		case "6":
			InsertCourses iii = new InsertCourses();
			String choice = "n";
			do {
				Course course = new Course();
				System.out.print("Enter Course Id: ");
				course.setCourseId(scanner.nextLine());
				
				System.out.print("Enter Instructor Id: ");
				course.setInstructorId(Integer.parseInt(scanner.nextLine()));
				
				System.out.print("Enter Credit Hours: ");
				course.setCreditHrs(Integer.parseInt(scanner.nextLine()));			
				
				System.out.print("Enter Semester: ");
				course.setSemester(scanner.nextLine());
				
				System.out.print("Enter Year Offered: ");
				course.setYear(Integer.parseInt(scanner.nextLine()));
				iii.inserts(connection, course);
				
				System.out.println("Are you done? y/n");
				choice = scanner.nextLine();
			}while(!"y".equals(choice));
			break;
		case "7":
			Joins.courseJoins(connection);
			break;
		case "8":
			for(int x=1;x <=10; x++) {
				boolean found = Util.rowExists(connection, x, "Instructor", "InstructorId");
				System.out.println("InstructorId : " + x + " " + found);
			}

			for(int x=1;x <=10; x++) {
				boolean found = Util.rowExists(connection, x, "Student", "StudentId");
				System.out.println("StudentId : " + x + " " + found);
			}

			for(int x=1;x <=10; x++) {
				boolean found = Util.rowExists(connection, x, "StudentGrades", "RowId");
				System.out.println("StudentGrades : " + x + " " + found);
			}
			break;
		case "9":
			System.out.println("Enter CourseId: ");
			String id = scanner.nextLine();		
			MTH1010.getInstructor(connection, id);
			break;
		case "10":
			List<FooBar> fooBarList = new ArrayList<>();
			for(int x=1;x<=1;x++) {
				FooBar f = new FooBar();
				System.out.println("Enter FooBar Col1: ");
				f.setFooBarCol(scanner.nextLine());
				System.out.println("Enter FooBar Col2: ");
				f.setFooBarCol1(scanner.nextLine());
				System.out.println("Enter FooBar Col3: ");
				f.setFooBarCol2(scanner.nextLine());
				System.out.println("Enter FooBar Col4: ");
				f.setFooBarCol3(scanner.nextLine());
				System.out.println("Enter FooBar Col5: ");
				f.setFooBarCol4(scanner.nextLine());	
				fooBarList.add(f);	
			}
			InsertFooBar.insertFoo(connection, fooBarList);
			break;
		case "11":
			for(int x=0; x<3;x++) {
				Fb1 fb = new Fb1();
					
				System.out.println("Enter FooBar Col1: ");
				fb.setCol1((scanner.nextLine()));
				System.out.println("Enter FooBar Col2: ");
				fb.setCol2((scanner.nextLine()));
				fb.setRowid(Simple.getNextNumber(connection));
			
				if(!Simple.rowExists(connection, fb.getCol1(), fb.getCol2())) {
					if (Simple.addRow(connection, fb)) {
						logger.debug("Row added..." + fb.toString());
					}else {
						logger.debug("Row not added..." + fb.toString());
					}
				}else {
					logger.debug("Row already exists..." + fb.toString());
				}
			}
			break;
		case "12":
			System.out.println("Enter Firstname: ");
			String fname = scanner.nextLine();
			System.out.println("Enter Lastname: ");
			String lname = scanner.nextLine();
			System.out.println("Enter Email: ");
			String email = scanner.nextLine();			
			int studentId = UTStudents.addStudent(connection, fname, lname, email);
			if (studentId > 0) {
				for(int x=1;x<=5;x++) {
					if ((x % 2) != 0) {
						UTStudents.addTransaction(connection, studentId, 1000 * x);
					}
					else {
						UTStudents.addTransaction(connection, studentId, -1000 * x);
					}
				}
			}
			else {
				logger.debug("Student not added.");
			}
			break;
		case "13":
				UTStudents.getBalanceByStudentID(connection);
			break;
		};
		
		/* Close the database connection */
		databaseManager.closeConnection(connection);
		scanner.close();
		logger.debug("Program completed");
	}
}
