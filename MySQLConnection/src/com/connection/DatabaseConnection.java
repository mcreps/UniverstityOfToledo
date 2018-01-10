package com.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.exceptions.DatabaseAccessException;
import com.exceptions.DriverNotFoundException;

/**
 * 
 * @author merl.crepsjr
 * @date 2017-Jan-9
 * Description: Creates and Maintains a database connection and it properties.
 * Current Supported database types: mysql, sqlserver, orcale, postgres, mariabd
 * 
 *
 */
public class DatabaseConnection {

	private String uri;
	private String driver;
	private String server;
	private String portNo;
	private String userName;
	private String password;
	private String defaultDatabase;
	private String databaseType;
	private Connection connection = null;
	private Properties prop;
	final static Logger logger = Logger.getLogger(DatabaseConnection.class);

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String ipAddress) {
		this.server = ipAddress;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDefaultDatabase() {
		return defaultDatabase;
	}

	public void setDefaultDatabase(String defaultDatabase) {
		this.defaultDatabase = defaultDatabase;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	private void loadProperties(Properties prop) {
		
		/* Load properties from Database properties from file */
		this.server = (String) prop.getProperty("server");
		this.portNo = (String) prop.getProperty("portno");
		this.userName = (String) prop.getProperty("username");
		this.password = (String) prop.getProperty("password");
		this.defaultDatabase = (String) prop.getProperty("defaultdatabase");
		this.databaseType = (String) prop.getProperty("databasetype");
		this.prop = prop;
		
		// Sets the uri to the correct connection string layout
		switch(this.databaseType.toLowerCase()) {
		case "mysql":
			this.uri = "jdbc:mysql://" + this.server + ":" + this.portNo + "/" + this.defaultDatabase;
			this.driver = "com.mysql.jdbc.Driver";
			break;
		case "sqlserver":
			/* jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]] */
			this.uri = "jdbc:sqlserver://" + this.server +";" 
					+ " databaseName=" + this.defaultDatabase 
					+":" + this.portNo + ";"
					+ "integratedSecurity=false";
			this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			break;
		case "orcale":
			this.uri = "jdbc:oracle:thin:@" + this.server + ":" + this.portNo + ":" + this.defaultDatabase;
			this.driver = "oracle.jdbc.OracleDriver";
			break;
		case "mariabd":
			this.uri = "jdbc:mariadb://" + this.server + ":" + this.portNo + "/" + this.defaultDatabase;
			this.driver = "org.mariadb.jdbc.Driver";
			break;
		case "postgres":
			this.uri = "jdbc:postgresql://" + this.server + ":" + this.portNo + "/" + this.defaultDatabase;
			this.driver = "org.postgresql.Driver";
			break;
		default :
			this.uri = "jdbc:mysql://" + this.server + ":" + this.portNo + "/" + this.defaultDatabase;
			this.driver = "com.mysql.jdbc.Driver";
		}

	}
	
	public DatabaseConnection() {
	}
	
	/**
	 * Loads the default setting from the Properties object.
	 * @param prop database connection properties class
	 */
	public DatabaseConnection(Properties prop) {
		loadProperties(prop);
	}
	
	/**
	 * Loads the default setting from the properties files.
	 * @param propertiesFile fully qualified path to properties file
	 */
	public DatabaseConnection(String propertiesFile) {
		logger.debug("Reading properties file.");
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);			
			loadProperties(prop);
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (Exception e) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
			}
		}		
	}
	
	/**
	 * Establish a MySQL Connection to the default database for the specified URL
	 * @return {@code null} if no connection can be made or  MySql Connection.
	 */
	public Connection establishMySqlConnection() {
			
		// Get Driver	
		logger.debug("Location suitable driver.");		
		try {
			Class.forName(this.driver).newInstance();
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.error("Suitable driver not found.  Driver: " + this.driver +  " Message:" + e.getLocalizedMessage());			
			logger.error(this);
			throw new DriverNotFoundException(e.getLocalizedMessage());
		}
		
		//Open connection
		logger.debug("Establishing specified database connection.");
		try {
			DriverManager.setLoginTimeout(15);
			this.connection = DriverManager.getConnection(this.uri, this.userName, this.password);
			logger.debug("Connection Established to " + this.server + ":" + this.portNo + "/" + this.defaultDatabase);
		} 
		catch (SQLException | RuntimeException e) {
			logger.error("No connected made to " + this.defaultDatabase + ".  Message:" + e.getLocalizedMessage());
			logger.error(this);
			throw new DatabaseAccessException(e.getLocalizedMessage());
		}
		return this.connection;
	}
	
	/**
	 * Closes a database connection
	 * @param connection
	 */
	public void closeConnection(Connection connection) {
		if (null != connection) {			
			try {
				logger.debug("Attempting to closing the database connection.");
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error(e.getLocalizedMessage());
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DatabaseConnection [uri=");
		builder.append(uri);
		builder.append(", driver=");
		builder.append(driver);
		builder.append(", server=");
		builder.append(server);
		builder.append(", portNo=");
		builder.append(portNo);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		if (null == password || "".equals(password)) {
			builder.append("");			
		}
		else {
			builder.append("********");			
		}
		builder.append(", defaultDatabase=");
		builder.append(defaultDatabase);
		builder.append(", databaseType=");
		builder.append(databaseType);
		builder.append(", connection=");
		builder.append(connection);
		builder.append(", prop={");
		builder.append("server=");
		builder.append(prop.getProperty("server"));
		builder.append(", defaultDatabase=");
		builder.append(prop.getProperty("defaultDatabase"));
		builder.append(", userName=");
		builder.append(prop.getProperty("userName"));
		builder.append(", driver=");
		builder.append(prop.getProperty("driver"));
		builder.append(", ipAddress=");
		builder.append(prop.getProperty("ipAddress"));
		builder.append("}");
		builder.append("]");
		return builder.toString();
	}
}
