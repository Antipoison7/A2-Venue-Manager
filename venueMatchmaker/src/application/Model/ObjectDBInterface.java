package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Model.ObjectClasses.Venue;

public class ObjectDBInterface extends JDBCHelper
{
	public Venue selectVenueNoType(int id) 
	{
		Venue v = null;
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT * FROM venues WHERE venue_id = ?;");
			query.setInt(1, id);
			
			ResultSet results = query.executeQuery();
			
			while(results.next()) 
			{
				v.setId(results.getInt(1));
				v.setName(results.getString(2));
				v.setHirePrice(results.getDouble(3));
				v.setCapacity(results.getInt(4));
				v.setCategory(results.getString(5));
				
				if(results.getInt(6) == 1) 
				{
					v.setBookable(true);
				}
				else 
				{
					v.setBookable(false);
				}
			}
			
			return v;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public void deleteVenueWhere(int id) 
	{
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("UPDATE venues SET bookable = 0 WHERE venue_id = ?;");
			query.setInt(1, id);

			
			query.executeUpdate();
			
			jdbc.close();
			query.close();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
}
