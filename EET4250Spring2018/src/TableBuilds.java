import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableBuilds {
	
	private static Logger logger = LoggerFactory.getLogger(TableBuilds.class);	
	
	private boolean tableExists(Connection connection, String tableName) {
		
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
	
	
	public void buildTables(Connection connection) {
		
		String instructorSql = "CREATE Table `Instructor` ( " + 
				"  `InstructorRowId` int(11) NOT NULL AUTO_INCREMENT, " + 
				"  `FirstName` varchar(50) NOT NULL DEFAULT '', " + 
				"  `LastName` varchar(50) NOT NULL DEFAULT '', " + 
				"  `ProfessorRating` varchar(15) NOT NULL DEFAULT '', " + 
				"  PRIMARY KEY (`InstructorRowId`) " + 
				") ENGINE=InnoDB DEFAULT CHARSET=latin1";
		
		String courseSql = "CREATE Table `Course` ( " + 
				"  `CourseRowId` int(11) NOT NULL AUTO_INCREMENT, " + 
				"  `CourseId` varchar(15) NOT NULL DEFAULT '', " + 
				"  `InstructorId` int(11) NOT NULL DEFAULT '0', " + 
				"  `CreditHrs` int(11) NOT NULL DEFAULT '0', " + 
				"  `Semester` varchar(15) NOT NULL DEFAULT '', " +
				"  `Year` int(4) NOT NULL DEFAULT '0000', " +
				"  PRIMARY KEY (`CourseRowId`), " + 
				"  UNIQUE KEY `KEY1` (`CourseId`,`Semester`,`Year`) "+
				") ENGINE=InnoDB DEFAULT CHARSET=latin1";
		
		logger.debug("Entering table builds");
		
		if (!tableExists(connection, "Course")) {
			try {
				PreparedStatement ps = connection.prepareStatement(courseSql);
				ps.executeUpdate(courseSql);
				logger.debug("Course table built");
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
		
		if (!tableExists(connection, "Instructor")) {
			try {
				PreparedStatement ps = connection.prepareStatement(instructorSql);
				ps.executeUpdate(instructorSql);
				logger.debug("Instructor table built");
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
	}
}
