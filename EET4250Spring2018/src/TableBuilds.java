import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableBuilds {
	
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
		
		boolean courseTableExists = true;
		boolean instructorTableExists = true;
		try {
			String sql = "SELECT * FROM Course";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
		}
		catch(SQLException e)
		{
			courseTableExists = false;
		}
		try {
			String sql = "SELECT * FROM Instructor";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
		}
		catch(SQLException e)
		{
			instructorTableExists = false;
		}		
		
		
		if (!courseTableExists) {
			try {
				PreparedStatement ps = connection.prepareStatement(courseSql);
				ps.executeUpdate(courseSql);
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
		
		if (!instructorTableExists) {
			try {
				PreparedStatement ps = connection.prepareStatement(instructorSql);
				ps.executeUpdate(instructorSql);
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
	}
}
