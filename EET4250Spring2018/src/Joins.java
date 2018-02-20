import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Joins {

	private static Logger logger = LoggerFactory.getLogger(Joins.class);	
	
	public static void courseJoins(Connection connection) {
		
		try {
			String sql = "SELECT * FROM Instructor " + 
					" LEFT JOIN  Course Using(InstructorId) " + 
					" WHERE NOT ISNULL(CourseRowId)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				logger.debug(rs.getString("FirstName") + " "
						   + rs.getString("Lastname"));
			}
		}
		catch (SQLException ex) {
			logger.debug("SQLException: " + ex.toString());
		}
		
		
	}
	
}
