import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UTStudents {

	
	private static Logger logger = LoggerFactory.getLogger(UTStudents.class);
	
	
	public static int addStudent(Connection connection, String fname, String lname, String email) {
		
		try {
			String sql = " INSERT INTO UT_Student (Fname, Lname, Email) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);		
			int rows = ps.executeUpdate();
			if (rows != 1) {
				logger.debug("Expected 1 but got " + rows);
			}else {
				logger.debug("Row added.");
			}
			
			sql = "SELECT StudentId FROM UT_Student ORDER BY StudentID DESC LIMIT 1";
			ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("StudentID");
			}
		}
		catch(SQLException e) {
			logger.debug("SQLException: " + e.toString());
		}
		return 0;	
	}
	
	public static void addTransaction(Connection connection, int studentId, double amount) {
		try {
			String sql = " INSERT INTO UT_Student_Transaction (StudentID, Amount) VALUES (?,?)";
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, studentId);
			ps.setDouble(2, amount);	
			int rows = ps.executeUpdate();
			if (rows != 1) {
				logger.debug("Expected 1 but got " + rows);
			}else {
				logger.debug("Row added.");
			}
		}
		catch(SQLException e) {
			logger.debug("SQLException: " + e.toString());
		}
	}
	
	public static void getBalanceByStudentID(Connection connection) {
		try {
			String sql = "SELECT Fname, Lname, Sum(Amount) As Amount FROM UT_Student_Transaction " + 
					"  JOIN UT_Student USING (StudentID) GROUP BY StudentID;";
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				logger.debug("Fname: " + rs.getString("Fname") + " Laname: " 
			                  + rs.getString("Lname")
			                  + " Balance: "+rs.getDouble("Amount"));
			}
		}
		catch(SQLException e) {
			logger.debug("SQLException: " + e.toString());
		}
	}
	
}
