package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Model.ObjectClasses.User;
import application.Model.ObjectClasses.Venue;

public class NewStaffMembers extends JDBCHelper {
	
	private static int authPin = 909;

	public boolean isStaffValid(User user) 
	{
		boolean isValid = true;
		
		try 
		{
			if(user.getUsername().length() <=0) 
			{
				isValid = false;
			}
			
			if(user.getPassword().length() <= 0);
			
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
				isValid = false;
			}
			
			if(user.getPassword().length() <= 0);
			
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
			
			if(code != authPin) 
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
	
	public boolean addNewStaff(User user) 
	{
		boolean isSuccessful = false;
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("INSERT INTO venues (venue_name, hire_price, capacity, category, bookable) VALUES(?,?,?,?,1);");
			query.setString(1, userVenue.getName());
			query.setDouble(2, userVenue.getHirePrice());
			query.setInt(3, userVenue.getCapacity());
			query.setString(4, userVenue.getCategory());
			
			if(query.executeUpdate() !=0) 
			{
				isSuccessful = true;
			}
			
			Statement statement = jdbc.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(venue_id) FROM venues;");
			
			int setID = rs.getInt(1);
			
			for(String s : userVenue.getSuitableType()) 
			{
				PreparedStatement typeQuery;
				
				typeQuery = jdbc.prepareStatement("INSERT INTO venues_suitable (venue_id, event_type) VALUES (?,?);");
				typeQuery.setInt(1, setID);
				typeQuery.setString(2, s);
				
				typeQuery.executeUpdate();
			}
		}
		catch(Exception e) 
		{
			return false;
		}
		
		return isSuccessful;
	}
}
