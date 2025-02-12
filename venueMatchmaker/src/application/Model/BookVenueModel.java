package application.Model;

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
			
			compatibility.put("Type", (r.getCategory().equals(v.getCategory())));
			
			compatibility.put("Event", v.getSuitableType().contains(r.getType()));
			
			ObjectDBInterface db = new ObjectDBInterface();
			ArrayList<Booking> bookings = db.getBookings(v.getName());
			
			compatibility.put("Double", true);
			
			for(Booking b : bookings) 
			{
				if(Booking.doesOverlap(r.getDate(), r.getTime(), r.getDuration(), b.getDate(), b.getTime(), b.getDuration())) 
				{
					compatibility.put("Double", false);
				}
			}			
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
			compScore++;
		}
		
		if(compat.get("Type")) 
		{
			compScore++;
		}
		
		if(compat.get("Event")) 
		{
			compScore++;
		}
		
		if(compat.get("Double")) 
		{
			compScore++;
		}
		
		return compScore;
	}
}
