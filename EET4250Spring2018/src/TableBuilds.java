import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableBuilds {
	
	public void buildTables(Connection connection) {
		

		
		boolean courseTableExists = true;
		boolean instructorTableExists = true;
		try {
			String sql = "SELECT * FROM Course";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
		}
		catch(SQLException e)
		{
			courseTableExists = false;
		}
		try {
			String sql = "SELECT * FROM Instructor";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
		}
		catch(SQLException e)
		{
			instructorTableExists = false;
		}		
		
		
		if (!courseTableExists) {
			try {
				PreparedStatement ps = connection.prepareStatement(courseSql);
				ps.executeUpdate(courseSql);
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
		
		if (!instructorTableExists) {
			try {
				PreparedStatement ps = connection.prepareStatement(instructorSql);
				ps.executeUpdate(instructorSql);
			}
			catch (SQLException e)
			{
				System.err.println(e.toString());
			}
		}
	}
}
