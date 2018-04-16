import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MTH1010 {

	private static Logger logger = LoggerFactory.getLogger(MTH1010.class);	
	public static void getInstructor(Connection connection, String courseid) {
		
		try {
			String sql = "SELECT * FROM Course " + 
					"Join Instructor ON InstructorId = InstructorRowId " + 
					"WHERE CourseId = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, courseid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				logger.debug("Instructor name: " + rs.getString("Firstname") + " "+ rs.getString("Lastname"));
			}
		}
		catch(SQLException e) {
			logger.debug("SQLException: " + e.toString());
		}
		
	}
	
	
}
