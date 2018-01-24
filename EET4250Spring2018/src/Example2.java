import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Example2 {

	/**
	 * Display all the Equipment Types
	 * @param connection
	 */
	public void displayEquipmentType(Connection connection) {
		
		String sql = "SELECT * FROM EquipmentType";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int rowdId = rs.getInt("RowdId");
				int equipmentId =  rs.getInt("EquipmentId");
				String name =  rs.getString("Name");
				Timestamp lastUpdated = rs.getTimestamp("LastUpdated");
			
				System.out.print("Rowid:"+String.valueOf(rowdId));
				System.out.print(" ,equipmentId:"+String.valueOf(equipmentId));
				System.out.print(" ,name:"+name);
				System.out.print(" ,lastUpdated:"+lastUpdated.toString());
				System.out.print("\n");
			}
		}
		catch(SQLException e) {
			System.err.println(e.toString());
		}
		
	}
}
