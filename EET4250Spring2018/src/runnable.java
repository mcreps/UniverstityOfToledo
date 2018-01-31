import java.sql.Connection;
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
		/* Open scanner for std inout */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Assignment Name: ");
		
		/* get the user input and store in name */
		String name = scanner.nextLine();
		
		if ("ex1".equals(name)) {
			logger.debug("Running Example 1");
			Example1 ex1 = new Example1();
			ex1.displayExample1(connection);
		}
		
		if ("ex2".equals(name)) {
			logger.debug("Running Example 2");
			Example2 ex2 = new Example2();
			ex2.displayEquipmentType(connection);
		}
		
		if ("ic1".equals(name)) {
			logger.debug("Running Inclass 1");
			TableBuilds tb = new TableBuilds();
			tb.buildTables(connection);
			
			InsertCourses insertCourses = new InsertCourses();
			insertCourses.inserts(connection);
		}
		
		if ("ii".equals(name)) {
			InsertInstructor ii = new InsertInstructor();
			ii.insertInstructor(connection);
		}

		
		/* Close the database connection */
		databaseManager.closeConnection(connection);
		logger.debug("Program completed");
	}
}
