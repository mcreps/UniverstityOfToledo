import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertInstructor {

	private static Logger logger = LoggerFactory.getLogger(InsertInstructor.class);	
	
	public void insertInstructor(Connection connection) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter FirstName: ");
		String fname = scanner.nextLine();	
		
		System.out.print("Enter LastName: ");
		String lname = scanner.nextLine();	

		System.out.print("Enter Professor Rating (0-5): ");
		String rating = scanner.nextLine();	
		
		String sql = "INSERT INTO Instructor " +
		"(FirstName, LastName, ProfessorRating) " + 
		" VALUES " + 
		"(?,?,?)";
		
		int rowsAffected = 0;
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, rating);
			rowsAffected = ps.executeUpdate();			
		}
		catch(SQLException e) {
			logger.debug(e.toString());
		}
		logger.debug("Rows Affected: "+rowsAffected);
	}
	
}
