import java.sql.Connection;

import com.connection.DatabaseConnection;

public class runnable {

	public static void main(String[] args) {

		DatabaseConnection databaseConnection = new DatabaseConnection("database.properties");			
		Connection connection = databaseConnection.establishMySqlConnection();
		
		

	}

}
