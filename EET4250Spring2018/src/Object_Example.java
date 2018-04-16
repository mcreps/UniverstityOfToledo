import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Object_Example {
	
	private static Logger logger = LoggerFactory.getLogger(Object_Example.class);	
	
	public static void fetch(Connection connection) {
		
		logger.debug("Entering fetch...");
		try {
			String sql = "SELECT ObjectName AS ObjName, " + 
					" MAX(Height) AS MH, " + 
					" Count(*) AS C" + 
					" FROM Data_Output " + 
					" JOIN Object ON Object.idObject = Data_Output.Object " + 
					" GROUP BY Object";
			PreparedStatement ps =connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				logger.debug("ObjectName: "+ rs.getString("ObjName") + 
						", MaxHeight: "+rs.getDouble("MH") + 
						", Count: "+rs.getInt("C"));
			}			
		}
		catch(SQLException e) {
			logger.debug("ESQLException: "+e.toString());
		}		
	}
}
