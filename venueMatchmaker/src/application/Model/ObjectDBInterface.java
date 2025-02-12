package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.Venue;

public class ObjectDBInterface extends JDBCHelper
{
	public Venue selectVenueNoType(String id) 
	{
		Venue v = new Venue();
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT * FROM venues WHERE venue_name = ?;");
			query.setString(1, id);
			
			ResultSet results = query.executeQuery();
			
			while(results.next()) 
			{
				v.setName(results.getString(1));
				v.setHirePrice(results.getDouble(2));
				v.setCapacity(results.getInt(3));
				v.setCategory(results.getString(4));
				
				if(results.getInt(5) == 1) 
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
			System.out.println(e);
			return null;
		}
	}
	
	public void deleteVenueWhere(String id) 
	{
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("UPDATE venues SET bookable = 0 WHERE venue_name = ?;");
			query.setString(1, id);

			
			query.executeUpdate();
			
			jdbc.close();
			query.close();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void toggleVenueWhere(String venueName) 
	{
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			//Ivanov, D. (n.d.). How to toggle boolean in SQLite. Noties. Reviewed Feb 10 2025, from https://noties.io/blog/2019/08/19/sqlite-toggle-boolean/index.html
			query = jdbc.prepareStatement("UPDATE venues SET bookable = ((bookable | 1) - (bookable & 1)) WHERE venue_name = ?;");
			
			query.setString(1, venueName);

			query.executeUpdate();
			
			jdbc.close();
			query.close();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void doesClientExist(String client) 
	{
		boolean doesExist = false;
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			
			query = jdbc.prepareStatement("SELECT COUNT(name) FROM clients WHERE name = ?;");
			query.setString(1, client);
			
			ResultSet results = query.executeQuery();
			
			if(results.getInt(1) > 0) 
			{
				doesExist = true;
			}
			
			if(!doesExist) 
			{
				query = jdbc.prepareStatement("INSERT INTO clients (name) VALUES (?);");
				query.setString(1, client);
				
				query.executeUpdate();
			}
			
			jdbc.close();
			query.close();
			
			
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public Request selectRequest(int id) 
	{
		Request r = new Request();
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT * FROM requests WHERE request_id = ?;");
			query.setInt(1, id);
			
			ResultSet results = query.executeQuery();
			
			while(results.next()) 
			{
				r.setRequestID(results.getInt(1));
				r.setClientName(results.getString(2));
				r.setTitle(results.getString(3));
				r.setArtist(results.getString(4));
				r.setDate(results.getString(5));
				r.setTime(results.getString(6));
				r.setDuration(results.getDouble(7));
				r.setAudienceNumber(results.getInt(8));
				r.setType(results.getString(9));
				r.setCategory(results.getString(10));
			}
			
			return r;
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return null;
		}
	}
	
	public ArrayList<Booking> getBookings(String venueID)
	{
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT * FROM event_bookings WHERE venue = ?;");
			query.setString(1, venueID);
			
			ResultSet results = query.executeQuery();
			
			while(results.next()) 
			{
				Booking r = new Booking();
				
				r.setRequestID(results.getInt(1));
				r.setClientName(results.getString(2));
				r.setStaff(results.getString(3));
				r.setCost(results.getDouble(4));
				r.setCommission(results.getDouble(5));
				r.setTitle(results.getString(6));
				r.setArtist(results.getString(7));
				r.setDate(results.getString(8));
				r.setTime(results.getString(9));
				r.setDuration(results.getDouble(10));
				r.setAudienceNumber(results.getInt(11));
				r.setType(results.getString(12));
				
				if(results.getInt(13) == 1) 
				{
					r.setGroup(true);
				}
				else 
				{
					r.setGroup(false);
				}
				r.setCategory(results.getString(14));
				
				r.setVenue(results.getString(15));
				
				bookings.add(r);
			}
			
			return bookings;
		}
		catch(Exception e) 
		{
			return bookings;
		}	
	}
}
