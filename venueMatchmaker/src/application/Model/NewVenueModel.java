package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Model.ObjectClasses.Venue;

public class NewVenueModel extends JDBCHelper {

	public boolean isEntryValid(Venue userVenue) 
	{
		boolean isValid = true;
		
		try 
		{
			if(userVenue.getCapacity() < 1) 
			{
				System.out.println("Small Capacity");
				isValid = false;
			}
			
			if((!userVenue.getCategory().equals("Indoor"))&&(!userVenue.getCategory().equals("Outdoor"))&&(!userVenue.getCategory().equals("Convertible"))) 
			{
				System.out.println("Categories");
				isValid = false;
			}
				
			if(userVenue.getHirePrice() < 0) 
			{
				System.out.println("Hire Price");
				isValid = false;
			}
				
			if(userVenue.getName().length() <=0) 
			{
				System.out.println("Name Length");
				isValid = false;
			}
			
			if(userVenue.getSuitableType().size()==0) 
			{
				System.out.println("Suitable Types");
				isValid = false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return isValid;
	}
	
	public boolean isDuplicateVenuename(String venueName) 
	{
		boolean isDuplicate = false;
		
		try 
		{
			Connection jdbc = connectDB();
			
			Statement statement = jdbc.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT venue_name FROM venues;");
			
			while(rs.next()) 
			{
				if(rs.getString(1).equals(venueName)) 
				{
					isDuplicate = true;
				}
			}
			
			statement.close();
			jdbc.close();
		}
		catch(Exception e) 
		{
			return true;
		}
		
		return isDuplicate;
	}
	
	public boolean addNewVenue(Venue userVenue) 
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
				typeQuery.close();
			}
			
			jdbc.close();
			query.close();
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return false;
		}
		
		return isSuccessful;
	}
}
