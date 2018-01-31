import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example1 {
	
	private static Logger logger = LoggerFactory.getLogger(Example1.class);
	/**
	 * Displays the Objects in the Object table
	 * @param connection
	 */
	public void displayExample1(Connection connection) {
		
		logger.debug("Entering displayExample1....");
		String sql = "SELECT * FROM Object WHERE idObject > ? AND ObjectWeight > ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, 10);
			ps.setDouble(2, 0.25);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				logger.debug("idObject: {}, ObjectName: {} , ObjectWeight: {}", rs.getInt("idObject"), rs.getString("ObjectName"),rs.getDouble("ObjectWeight"));
			}

		} catch (SQLException e) {
			logger.error(e.toString());
		}	
	}	
}
