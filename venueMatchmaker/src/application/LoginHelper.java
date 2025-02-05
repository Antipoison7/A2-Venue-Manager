package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHelper extends JDBCHelper
{
	//Check the database to see if the username and password are valid and if the security level is sufficient
	public boolean doesPassSecurity(String username, String password, int securityLevel) 
	{
		try {
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT password FROM users WHERE username = ? AND security >= ?");
			query.setString(1, username);
			query.setInt(2, securityLevel);
			
			ResultSet results = query.executeQuery();
			
			boolean isValidPassword = false;
			
			while(results.next()) 
			{
				if(password.equals(results.getString(1))) 
				{
					isValidPassword = true;
				}
			}
			
			return isValidPassword;
			
		} catch (SQLException e) {
			ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("DbFailedToOpen");
	    	errorThrow.setErrorBody(e.toString());
	    	
	    	errorThrow.throwError();
	    	
	    	return false;
		}
	}
	
	//Checks the database to see if the username and password matches, yes this should use a hash checker, no I don't have time sorry
	public boolean isLoginValid(String username, String password) 
	{
		try {
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT password FROM users WHERE username = ?");
			query.setString(1, username);
			
			ResultSet results = query.executeQuery();
			
			boolean isValidPassword = false;
			
			while(results.next()) 
			{
				if(password.equals(results.getString(1))) 
				{
					isValidPassword = true;
				}
			}
			
			return isValidPassword;
			
		} catch (SQLException e) {
			ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("DbFailedToOpen");
	    	errorThrow.setErrorBody(e.toString());
	    	
	    	errorThrow.throwError();
	    	
	    	return false;
		}
	}
	
	//Checks the database to see if an account is tied to the selected username, returns true if the account is found or false if it is not
	public boolean doesAccountExist(String username) 
	{
		try {
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT COUNT(username) FROM users WHERE username = ?");
			query.setString(1, username);
			
			ResultSet results = query.executeQuery();
			
			if(results.getInt(1) >= 1) 
			{
				query.close();
				jdbc.close();
				return true;
			}
			else 
			{
				query.close();
				jdbc.close();
				return false;
			}
			
		} catch (SQLException e) {
			ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("DbFailedToOpen");
	    	errorThrow.setErrorBody(e.toString());
	    	
	    	errorThrow.throwError();
	    	
	    	return false;
		}
	}
}
