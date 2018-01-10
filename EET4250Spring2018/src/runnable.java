import java.sql.Connection;

import com.connection.DatabaseConnection;

public class runnable {

	public static void main(String[] args) {

		/* Make a database connections with properties files */
		DatabaseConnection databaseConnection = new DatabaseConnection("database.properties");			
		Connection connection = databaseConnection.establishMySqlConnection();
		if (null == connection) {
			System.exit(1);
		}
		
		
		/* Close the database connection */
		databaseConnection.closeConnection(connection);
	}

}
