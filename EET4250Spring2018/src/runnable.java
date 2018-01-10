import java.sql.Connection;

import com.connection.DatabaseManager;

public class runnable {

	public static void main(String[] args) {

		/* Make a database connections with properties files */
		DatabaseManager databaseManager = new DatabaseManager("database.properties");			
		Connection connection = databaseManager.establishConnection();
		if (null == connection) {
			System.exit(1);
		}
				
		/* Close the database connection */
		databaseManager.closeConnection(connection);
	}

}
