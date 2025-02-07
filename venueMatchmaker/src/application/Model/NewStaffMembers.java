package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Model.ObjectClasses.User;
import application.Model.ObjectClasses.Venue;

public class NewStaffMembers extends JDBCHelper {
	
	private final static int AUTH_PIN = 909;

	public boolean isStaffValid(User user) 
	{
		boolean isValid = true;
		
		try 
		{
			if(user.getUsername().length() <=0) 
			{
				isValid = false;
			}
			
			if(user.getPassword().length() <= 0)
			{
				isValid = false;
			}
			
			if(user.getRealName().length() <= 0) 
			{
				isValid = false;
			}
			
			if(user.getSecurity() != 0) 
			{
				isValid = false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return isValid;
	}
	
	public boolean isManagerValid(User user, int code) 
	{
		boolean isValid = true;
		
		try 
		{
			if(user.getUsername().length() <=0) 
			{
				System.out.println("Too Short Username");
				isValid = false;
			}
			
			if(user.getPassword().length() <= 0)
			{
				System.out.println("Too Short Password |" + user.getPassword() + " | " + user.getPassword().length());
				isValid = false;
			}
			
			if(user.getRealName().length() <= 0) 
			{
				System.out.println("Too Short Real Name");
				isValid = false;
			}
			
			if(user.getSecurity() != 1) 
			{
				System.out.println("Invalid Manager Security Level");
				isValid = false;
			}
			
			if(code != AUTH_PIN) 
			{
				System.out.println("Invalid Authpin");
				isValid = false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return isValid;
	}
	
	public boolean addNewStaff(User user, int security) 
	{
		boolean isSuccessful = false;
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("INSERT INTO users (username, password, real_name, security) VALUES(?,?,?,?);");
			query.setString(1, user.getUsername());
			query.setString(2, user.getPassword());
			query.setString(3, user.getRealName());
			query.setInt(4, security);
			
			if(query.executeUpdate() !=0) 
			{
				isSuccessful = true;
			}
		}
		catch(Exception e) 
		{
			return false;
		}
		
		return isSuccessful;
	}
}
