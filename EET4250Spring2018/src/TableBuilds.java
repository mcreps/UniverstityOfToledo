import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableBuilds {
	
	private static final Logger logger = LoggerFactory.getLogger(TableBuilds.class);
	
	/**
	 * 
	 * @param connection
	 * @param tableName
	 * @return
	 */
	public static boolean tableExists(Connection connection, String tableName) {
		
		logger.debug("Entering tableExists connection: {}, tableName: {}", new Object[]{connection, tableName});
		try {
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}
	
	
	/**
	 * builds the required tables for EET4250
	 * @param connection
	 */
	public static void buildTables(Connection connection) {
		
		logger.debug("Entering table builds");
		HashMap<String, String> tables = new HashMap<>();
		
		String sql = "CREATE Table `Instructor` ( " + 
				"  `InstructorRowId` int(11) NOT NULL AUTO_INCREMENT, " + 
				"  `FirstName` varchar(50) NOT NULL DEFAULT '', " + 
				"  `LastName` varchar(50) NOT NULL DEFAULT '', " + 
				"  `ProfessorRating` varchar(15) NOT NULL DEFAULT '', " + 
				"  PRIMARY KEY (`InstructorRowId`) " + 
				") ENGINE=InnoDB DEFAULT CHARSET=latin1";
		tables.put("Instructor", sql);
		
		sql = "CREATE Table `Course` ( " + 
				"  `CourseRowId` int(11) NOT NULL AUTO_INCREMENT, " + 
				"  `CourseId` varchar(15) NOT NULL DEFAULT '', " + 
				"  `InstructorId` int(11) NOT NULL DEFAULT '0', " + 
				"  `CreditHrs` int(11) NOT NULL DEFAULT '0', " + 
				"  `Semester` varchar(15) NOT NULL DEFAULT '', " +
				"  `Year` int(4) NOT NULL DEFAULT '0000', " +
				"  PRIMARY KEY (`CourseRowId`), " + 
				"  UNIQUE KEY `KEY1` (`CourseId`,`Semester`,`Year`) "+
				") ENGINE=InnoDB DEFAULT CHARSET=latin1";
		tables.put("Course", sql);
	
		sql = "CREATE TABLE `Student` ( " +
				  " `StudentId` int(11) NOT NULL AUTO_INCREMENT, " + 
				  " `Firstname` varchar(45) DEFAULT '', " + 
				  " `Lastname` varchar(45) DEFAULT ''," + 
				  " `Address` varchar(45) DEFAULT ''," + 
				  " `City` varchar(45) DEFAULT ''," + 
				  " `State` varchar(2) DEFAULT ''," + 
				  " `PostCode` varchar(45) DEFAULT ''," + 
				  " `Email` varchar(128) DEFAULT ''," + 
				  " `PhoneNo` varchar(15) DEFAULT ''," + 
				  " PRIMARY KEY (`StudentId`)," + 
				  " UNIQUE KEY `KEY1` (`Firstname`,`Lastname`)" + 
				  " ) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		tables.put("Student", sql);
		
		sql = "CREATE TABLE `StudentGrades` ( " +
				" `RowId` int(11) NOT NULL AUTO_INCREMENT, " + 
				" `LinkerId` int(11) NOT NULL, " + 
				" `StudentId` int(11) NOT NULL, " + 
				" `GradeRecieved` char(2) NOT NULL DEFAULT '', " + 
				" `SemesterTaken` varchar(45) NOT NULL DEFAULT '', " + 
				" PRIMARY KEY (`RowId`) " + 
				" ) ENGINE=InnoDB DEFAULT CHARSET=latin1";
		tables.put("StudentGrades", sql);
		
		
		//  Add all the tables
		for(Map.Entry<String, String> entry : tables.entrySet()) {
			String tableName = entry.getKey();
		    sql = entry.getValue();
			
		    if (!tableExists(connection, tableName)) {
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.executeUpdate(sql);
					logger.debug("Course table built");
				}
				catch (SQLException e)
				{
					logger.debug("SQLException: " + e.toString());
				}
			}
		    else {
		    	logger.debug("Table " + tableName + " already exists.");
		    }
		}
	}
}
