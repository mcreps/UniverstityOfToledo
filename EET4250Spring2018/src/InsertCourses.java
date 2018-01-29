import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCourses {

	
	public void inserts(Connection connection) {
		
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
			System.err.println(e.toString());
		}
			
		
	}
}
