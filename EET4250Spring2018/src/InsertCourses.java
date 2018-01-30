import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertCourses {

	private static Logger logger = LoggerFactory.getLogger(runnable.class);
	
	public void inserts(Connection connection) {
		
		logger.debug("Entering inserts.");

		String sql = "INSERT INTO Course ( " + 
		" CourseRowId, CourseId, InstructorId, CreditHrs ) " + 
		" VALUES (?, ?, ? ,?) ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(2, "EET 4250");
			ps.setInt(3, 0);
			ps.setInt(4, 4);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows Affected: "+rowsAffected);
		}
		catch (SQLException e) {
			logger.error(e.toString());
		}	
	}
}
