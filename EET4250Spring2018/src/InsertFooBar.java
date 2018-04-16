import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertFooBar {
	private static Logger logger = LoggerFactory.getLogger(InsertFooBar.class);
	
	public static void insertFoo(Connection connection, List<FooBar> list) {
		String sql = "INSERT INTO FooBar " + 
				"  (FooBarCol,FooBarCol1,FooBarCol2,FooBarCol3,FooBarCol4) " + 
				"  VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			for(FooBar f : list) {
				ps.setString(1, f.getFooBarCol());
				ps.setString(2, f.getFooBarCol1());
				ps.setString(3, f.getFooBarCol2());
				ps.setString(4, f.getFooBarCol3());
				ps.setString(5, f.getFooBarCol4());
				if (  ps.executeUpdate() !=1) {
					logger.debug("Row not updated properly.  " + f.toString());
				}else {
					logger.debug("Row added.  " + f.toString());
				}
			}
		}
		catch(SQLException e) {
			logger.debug("SQLException: " + e.toString());
		}	
	}
}
