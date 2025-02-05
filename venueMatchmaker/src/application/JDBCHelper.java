package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCHelper 
{
	private static String pathToDB = "jdbc:sqlite:src/application/resources/bookingDB.db";
	
	public Connection connectDB() 
	{
		try {
			return DriverManager.getConnection(pathToDB);
		} catch (SQLException e) 
		{
			ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("DbFailedToOpen");
	    	errorThrow.setErrorBody(e.toString());
	    	
	    	errorThrow.throwError();
	    	return null;
		}
	}
}
