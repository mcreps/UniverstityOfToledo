import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Example1 {

	/**
	 * Displays the Objects in the Object table
	 * @param connection
	 */
	public void displayExample1(Connection connection) {
		String sql = "SELECT * FROM Object WHERE idObject > ? AND ObjectWeight > ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, 10);
			ps.setDouble(2, 0.25);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.printf("idObject: %d, ObjectName: %s, ObjectWeight: %f\n", 
						rs.getInt("idObject"),
						rs.getString("ObjectName"), 
						rs.getDouble("ObjectWeight"));
			}

		} catch (SQLException e) {
			System.err.println(e.toString());
		}	
	}	
}
