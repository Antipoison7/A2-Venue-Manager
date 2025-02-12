package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import application.Controller.BookVenueController;
import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.Venue;
import application.Model.ObjectClasses.VenueDump;

public class AutoMatchModel extends JDBCHelper
{
	//Returns true or false depending on if a match is found and sets the static variables in BookVenueController if found.
	public boolean autoMatch(int requestID) 
	{
		boolean foundMatch = false;
		
		try 
		{
			ObjectDBInterface db = new ObjectDBInterface();
			Request request = db.selectRequest(requestID);
			AllVenuesSearchModel v = new AllVenuesSearchModel();
			BookVenueModel compatibility = new BookVenueModel();
			
			ArrayList<Venue> venues = getVenues();
			ArrayList<Integer> matchVal = new ArrayList<Integer>(); 
			
			for(int i = 0; i < venues.size(); i++) 
			{
				matchVal.add(0);
			}
			
			//Remove venues that have overlaps
			for(int i = venues.size()-1; i >= 0; i--) 
			{
				boolean hasOverlap = false;
				for(Booking b : db.getBookings(venues.get(i).getName())) 
				{
					if(Request.doesOverlap(request.getDate(), request.getTime(), request.getDuration(), b.getDate(), b.getTime(), b.getDuration())) 
					{
						hasOverlap = true;
					}
				}
				if(hasOverlap) 
				{
					matchVal.remove(i);
					venues.remove(i);
				}
			}
			
			//If there are no available venues, return that there are no venues available
			if(venues.size() == 0) 
			{
				return false;
			}
			
			//Calculate Compatibility
			for(int i = 0; i < venues.size(); i++) 
			{
				HashMap<String, Boolean> venueCompat = compatibility.calculateCompatibility(request, venues.get(i));
				int count = 0;
				
				if(venueCompat.get("Capacity")) 
				{
					count++;
				}
				
				if(venueCompat.get("Type")) 
				{
					count++;
				}
				
				if(venueCompat.get("Event")) 
				{
					count++;
				}
				
				matchVal.set(i, count);
			}
			
			int maxCompatibility = 0;
			//Calculate Max Compatibility
			for(int i : matchVal) 
			{
				if(i > maxCompatibility) 
				{
					maxCompatibility = i;
				}
			}
			
			//Remove Values lower than the max
			for(int i = venues.size()-1; i >= 0; i--) 
			{
				if(matchVal.get(i) < maxCompatibility) 
				{
					venues.remove(i);
					matchVal.remove(i);
				}
			}
			
			if(venues.size() != 0) 
			{
				BookVenueController.setVenueName(venues.get(0).getName());
				BookVenueController.setRequestID(requestID);
				
				return true;
			}
			
		}
		catch(Exception e) 
		{
			return false;
		}
		
		return foundMatch;
	}
	
	
	//Returns an ArrayList of all venues in the database.
	public ArrayList<Venue> getVenues()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM venues");
			
			ArrayList<Venue> venues = new ArrayList<Venue>();
			
			while(resultSet.next()) 
			{
				VenueDump newVenue = new VenueDump();
				newVenue.setName(resultSet.getString(1));
				newVenue.setHirePrice(resultSet.getDouble(2));
				newVenue.setCapacity(resultSet.getInt(3));
				newVenue.setCategory(resultSet.getString(4));
				
				if(resultSet.getInt(5) == 1) 
				{
					newVenue.setBookable(true);
				}
				else 
				{
					newVenue.setBookable(false);
				}
				
				PreparedStatement query;
				query = jdbc.prepareStatement("SELECT event_type FROM venues_suitable WHERE venue_id = ?;");
				query.setString(1, resultSet.getString(1));
				
				ResultSet results = query.executeQuery();
				
				while(results.next()) 
				{
//					System.out.println("Ping");
					newVenue.addToType(results.getString(1));
				}
				
				venues.add(newVenue);
			}
			
			jdbc.close();
			statement.close();
			
			return venues;
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return null;
		}
	}
}