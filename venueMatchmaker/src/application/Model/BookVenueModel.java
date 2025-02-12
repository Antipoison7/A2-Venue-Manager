package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.Venue;

public class BookVenueModel extends JDBCHelper
{
	public HashMap<String, Boolean> calculateCompatibility(Request r, Venue v) 
	{
		HashMap<String, Boolean> compatibility = new HashMap<String,Boolean>();
		compatibility.put("Capacity", false);
		compatibility.put("Type", false);
		compatibility.put("Event", false);
		compatibility.put("Double", false);
		
		try 
		{
			compatibility.put("Capacity", (r.getAudienceNumber()<= v.getCapacity()) );
			
			String type = r.getCategory().substring(0, 1).toUpperCase() + r.getCategory().substring(1);
			compatibility.put("Type", (v.getCategory().equals(type)) );
			
			compatibility.put("Event", v.getSuitableType().contains(r.getType().toLowerCase()));
//			System.out.println("Event R Type = " + r.getType());
//			System.out.println("Event V Type = " + v.getSuitableType());
			
			ObjectDBInterface db = new ObjectDBInterface();
			ArrayList<Booking> bookings = db.getBookings(v.getName());
			
			compatibility.put("Double", true);
			
			for(Booking b : bookings) 
			{
//				System.out.println("Booking: " + b.getTitle());
				
				if(Booking.doesOverlap(r.getDate(), r.getTime(), r.getDuration(), b.getDate(), b.getTime(), b.getDuration())) 
				{
//					System.out.println("Overlap: " + b.getTitle());
					compatibility.put("Double", false);
				}
			}
//			System.out.println("Double Booking=" + compatibility.get("Double"));
		}
		catch(Exception e) 
		{
			compatibility.put("Capacity", false);
			compatibility.put("Type", false);
			compatibility.put("Event", false);
			compatibility.put("Double", false);
		}
		
		
		return compatibility;
	}
	
	public int getCompatibilityNumber(Request r, Venue v) 
	{
		HashMap<String, Boolean> compat = calculateCompatibility(r,v);
		
		int compScore = 0;
		
		if(compat.get("Capacity")) 
		{
			System.out.println("+Capac");
			compScore++;
		}
		
		if(compat.get("Type")) 
		{
			System.out.println("+Type");
			compScore++;
		}
		
		if(compat.get("Event")) 
		{
			System.out.println("+Event");
			compScore++;
		}
		
		if(compat.get("Double")) 
		{
			System.out.println("+Double");
			compScore++;
		}
		
		return compScore;
	}
	
	public boolean addBookingToVenue(Booking b) 
	{
		boolean isSuccessful = true;
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			
			query = jdbc.prepareStatement("INSERT INTO event_bookings(client_name, staff_username, cost, commission, title, artist, date, start_time, duration, audience_number, type, group_booking, category, venue) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			
			query.setString(1, b.getClientName());
			query.setString(2, b.getStaff());
			query.setDouble(3, b.getCost());
			query.setDouble(4, b.getCommission());
			query.setString(5, b.getTitle());
			query.setString(6, b.getArtist());
			query.setString(7, b.getDate());
			query.setString(8, b.getTime());
			query.setDouble(9, b.getDuration());
			query.setInt(10, b.getAudienceNumber());
			query.setString(11, b.getType());
			
			if(b.getGroup()) 
			{
				query.setInt(12, 1);
			}
			else 
			{
				query.setInt(12, 0);
			}
			query.setString(13, b.getCategory());
			query.setString(14, b.getVenue());

			
			query.executeUpdate();
			
			
			query.close();
			
			jdbc.close();
			
			isSuccessful = true;
			
		}
		catch(Exception e) 
		{
			return false;
		}
		
		return isSuccessful;
	}
	
	public boolean removeRequest(int i) 
	{
boolean isSuccessful = true;
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			
			query = jdbc.prepareStatement("DELETE FROM requests WHERE request_id = ?;");

			query.setInt(1, i);
			
			query.executeUpdate();
			
			
			query.close();
			
			jdbc.close();
			
			isSuccessful = true;
			
		}
		catch(Exception e) 
		{
			return false;
		}
		
		return isSuccessful;
	}
}
