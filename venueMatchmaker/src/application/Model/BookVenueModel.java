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
			
			String type = r.getCategory().substring(0, 1).toUpperCase() + r.getCategory().substring(1);
			compatibility.put("Type", (v.getCategory().equals(type)) );
			
			compatibility.put("Event", v.getSuitableType().contains(r.getType()));
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
}
