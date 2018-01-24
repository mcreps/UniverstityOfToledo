import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.connection.DatabaseManager;

public class runnable {

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
			Example1 ex1 = new Example1();
			ex1.displayExample1(connection);
		}
		
		if ("ex2".equals(name)) {
			Example2 ex2 = new Example2();
			ex2.displayEquipmentType(connection);
		}

		/* Close the database connection */
		databaseManager.closeConnection(connection);
	}

}
