import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertCourses {

	private static Logger logger = LoggerFactory.getLogger(runnable.class);
	
	public void inserts(Connection connection) {
		
		Scanner scanner = new Scanner(System.in);
		
		logger.debug("Entering inserts.");

		String sql = "INSERT INTO Course ( " + 
		" CourseId, InstructorId, CreditHrs, Semester, Year ) " + 
		" VALUES (?, ? ,?, ?, ?) ";
		
		int rowsAffected=0;
		
		for (int x=0;x<9;x++) {
			System.out.print("Enter Course Id: ");
			String coursId = scanner.nextLine();
			
			System.out.print("Enter Instructor Id: ");
			int instructorId = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Enter Credit Hours: ");
			int creditHours = Integer.parseInt(scanner.nextLine());			
			
			System.out.print("Enter Semester: ");
			String semester = scanner.nextLine();
			
			System.out.print("Enter Year Offered: ");
			int yearOffered = Integer.parseInt(scanner.nextLine());
			
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, coursId);
				ps.setInt(2, instructorId);
				ps.setInt(3, creditHours);
				ps.setString(4, semester);
				ps.setInt(5, yearOffered);
				rowsAffected =+ ps.executeUpdate();
			}
			catch (SQLException e) {
				logger.error(e.toString());
			}
		}	
		logger.debug("Rows Affected: "+rowsAffected);
	}
}
