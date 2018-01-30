import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example2 {

	private static Logger logger = LoggerFactory.getLogger(Example2.class);
	
	/**
	 * Display all the Equipment Types
	 * @param connection
	 */
	public void displayEquipmentType(Connection connection) {
		
		logger.debug("Entering displayEquipmentType....");
		String sql = "SELECT * FROM EquipmentType";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int rowdId = rs.getInt("RowdId");
				int equipmentId =  rs.getInt("EquipmentId");
				String name =  rs.getString("Name");
				Timestamp lastUpdated = rs.getTimestamp("LastUpdated");
			
				logger.debug("rowId: {} , equipmentId: {}, name: {}, lastUpdated:{}",String.valueOf(rowdId),String.valueOf(equipmentId), name, lastUpdated.toString());
			}
		}
		catch(SQLException e) {
			System.err.println(e.toString());
		}
		
	}
}
