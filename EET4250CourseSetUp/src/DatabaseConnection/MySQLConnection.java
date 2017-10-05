package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnection extends DatabaseConnection {

	private final static String driver = "com.mysql.jdbc.Driver";
	private String serverIpAddress;
	private String userName;
	private String password;
	private String defaultDatabase;
	private Connection connection = null;

	public MySQLConnection(Properties prop) {
		// Database properties from file
		this.serverIpAddress = (String) prop.getProperty("serverIpAddress");
		this.userName = (String) prop.getProperty("userName");
		this.password = (String) prop.getProperty("password");
		this.defaultDatabase = (String) prop.getProperty("defaultDatabase");
	}

	@Override
	Connection getDatabaseConneciton() {
		// Get Driver
		String uri = "jdbc:mysql://" + this.serverIpAddress + "/" + this.defaultDatabase;
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			System.out.println("Error: Driver not found.  " + e.getLocalizedMessage());
			return connection;
		}

		// Open connection
		try {
			connection = DriverManager.getConnection(uri, this.userName, this.password);
			System.out.println("Connected to the database");
		} catch (Exception e) {
			System.out.println("NO CONNECTION.  " + e.toString());
		}
		return connection;
	}
}
