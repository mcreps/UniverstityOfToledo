import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableBuilds {
	
	private static Logger logger = LoggerFactory.getLogger(TableBuilds.class);	
	
	private boolean tableExists(Connection connection, String tableName) {
		
		logger.debug("Entering tableExists connection: {}, tableName: {}", new Object[]{connection, tableName});
		try {
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}
	
	
	public void buildTables(Connection connection) {
		
		logger.debug("Entering table builds");
		
		String courseSql = "";
		String instructorSql ="";
			
		if (!tableExists(connection, "Course")) {
			try {
				PreparedStatement ps = connection.prepareStatement(courseSql);
				ps.executeUpdate(courseSql);
				logger.debug("Course table built");
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
		
		if (!tableExists(connection, "Instructor")) {
			try {
				PreparedStatement ps = connection.prepareStatement(instructorSql);
				ps.executeUpdate(instructorSql);
				logger.debug("Instructor table built");
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
	}
}
