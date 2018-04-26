import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simple {
	private static Logger logger = LoggerFactory.getLogger(Simple.class);	
	
	
	public static int getNextNumber(Connection connection) {
		int rowid = 0;
		String sql = "SELECT MAX(RowId) FROM FooBar_1";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				rowid = rs.getInt("MAX(RowId)");
			}
		}
		catch(SQLException e) {
			logger.debug(e.toString());
		}
		return rowid + 1;
	}

	public static boolean rowExists(Connection connection, String col1, String col2) {
		String sql = "SELECT * FROM FooBar_1 WHERE Col1=? AND Col2=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, col1);
			ps.setString(2, col2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}
		catch(SQLException e) {
			logger.debug(e.toString());
		}
		return false;
	}
		
	public static boolean addRow(Connection connection, Fb1 fb) {
		
		String sql = "INSERT INTO FooBar_1 (RowId, Col1, Col2) VALUES (?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, fb.getRowid());
			ps.setString(2, fb.getCol1());
			ps.setString(3, fb.getCol2());
			int x = ps.executeUpdate();
			if (x!=1) {
				return false;
			}
		}
		catch(SQLException e) {
			logger.debug(e.toString());
		}	
		return true;
	}
}
