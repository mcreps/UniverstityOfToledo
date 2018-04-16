import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {

	public static boolean rowExists(Connection connection, int value, String tableName, String colName) {
		
		try {
			String sql = "SELECT * FROM " + tableName +  " WHERE " + colName +" = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, value);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}
		catch(SQLException e) {
		}
		return false;
	}
	
}
