package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.User;
import application.Model.ObjectClasses.Venue;
import application.Model.ObjectClasses.VenueDump;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableListGenerator extends JDBCHelper{
	public ObservableList<User> getUsers()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE active = 1");
			
			ObservableList<User> users = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				User newUser = new User();
				newUser.setUsername(resultSet.getString(1));
//				System.out.println(resultSet.getString(1));
				newUser.setPassword(resultSet.getString(2));
//				System.out.println(resultSet.getString(2));
				newUser.setRealName(resultSet.getString(3));
//				System.out.println(resultSet.getString(3));
				newUser.setSecurity(resultSet.getInt(4));
//				System.out.println(resultSet.getInt(4));
				users.add(newUser);
			}
			
			jdbc.close();
			statement.close();
			
			return users;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public ObservableList<Venue> getVenues()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM venues WHERE bookable = 1");
			
			ObservableList<Venue> venues = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				Venue newVenue = new Venue();
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
				
				venues.add(newVenue);
			}
			
			jdbc.close();
			statement.close();
			
			return venues;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public ObservableList<VenueDump> getVenueDump()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM venues");
			
			ObservableList<VenueDump> venues = FXCollections.observableArrayList();
			
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
	
	public ObservableList<Request> getRequests()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT request_id, client_name, title, artist FROM requests");
			
			ObservableList<Request> requests = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				Request newRequest = new Request();
				newRequest.setRequestID(resultSet.getInt(1));
				newRequest.setClientName(resultSet.getString(2));
				newRequest.setTitle(resultSet.getString(3));
				newRequest.setArtist(resultSet.getString(4));
				
				requests.add(newRequest);
			}
			
			jdbc.close();
			statement.close();
			
			return requests;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public ObservableList<Booking> getEvents()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM event_bookings");
			
			ObservableList<Booking> bookings = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				Booking newBooking = new Booking();
				newBooking.setRequestID(resultSet.getInt(1));
				newBooking.setClientName(resultSet.getString(2));
				newBooking.setStaff(resultSet.getString(3));
				newBooking.setCost(resultSet.getDouble(4));
				newBooking.setCommission(resultSet.getDouble(5));
				newBooking.setTitle(resultSet.getString(6));
				newBooking.setArtist(resultSet.getString(7));
				newBooking.setDate(resultSet.getString(8));
				newBooking.setTime(resultSet.getString(9));
				newBooking.setDuration(resultSet.getDouble(10));
				newBooking.setAudienceNumber(resultSet.getInt(11));
				newBooking.setType(resultSet.getString(12));
				newBooking.setGroup(resultSet.getBoolean(13));
				newBooking.setCategory(resultSet.getString(14));
				newBooking.setVenue(resultSet.getString(15));
				
				bookings.add(newBooking);
			}
			
			jdbc.close();
			statement.close();
			
			return bookings;
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return null;
		}
	}
	
	public ObservableList<Booking> getEventsVenue(String s)
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			PreparedStatement query;
			
			query = jdbc.prepareStatement("SELECT * FROM event_bookings WHERE venue = ?");
			
			query.setString(1, s);
			
			ResultSet resultSet = query.executeQuery();
			
			ObservableList<Booking> bookings = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				Booking newBooking = new Booking();
				newBooking.setRequestID(resultSet.getInt(1));
				newBooking.setClientName(resultSet.getString(2));
				newBooking.setStaff(resultSet.getString(3));
				newBooking.setCost(resultSet.getDouble(4));
				newBooking.setCommission(resultSet.getDouble(5));
				newBooking.setTitle(resultSet.getString(6));
				newBooking.setArtist(resultSet.getString(7));
				newBooking.setDate(resultSet.getString(8));
				newBooking.setTime(resultSet.getString(9));
				newBooking.setDuration(resultSet.getDouble(10));
				newBooking.setAudienceNumber(resultSet.getInt(11));
				newBooking.setType(resultSet.getString(12));
				newBooking.setGroup(resultSet.getBoolean(13));
				newBooking.setCategory(resultSet.getString(14));
				newBooking.setVenue(resultSet.getString(15));
				
				bookings.add(newBooking);
			}
			
			jdbc.close();
			query.close();
			
			return bookings;
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return null;
		}
	}
	
	public ObservableList<String> getVenueTypes(String i)
	{
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT event_type FROM venues_suitable WHERE venue_id = ?;");
			query.setString(1, i);
			
			ObservableList<String> types = FXCollections.observableArrayList();
			
			ResultSet results = query.executeQuery();
			
			while(results.next()) 
			{
				types.add(results.getString(1));
			}
			
			jdbc.close();
			query.close();
			
			return types;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public ObservableList<Venue> getVenuesFilter(HashMap<String, Boolean> filters, int requestID)
	{
		try 
		{
			ObjectDBInterface db = new ObjectDBInterface();
			Request r = db.selectRequest(requestID);
			
			Connection jdbc =  connectDB();
			
			PreparedStatement query;
			String queryString = "SELECT v.venue_name, v.hire_price, v.capacity, v.category, v.bookable, s.event_type FROM venues as v JOIN venues_suitable as s ON v.venue_name = s.venue_id WHERE bookable = 1";
			
			if(filters.get("Capacity")) 
			{
//				System.out.println("Capacity");
				queryString += " AND capacity >= ?";
			}
			
			if(filters.get("Type")) 
			{
//				System.out.println("Type");
				queryString += " AND event_type = ?";
			}
			
			if(filters.get("Weather")) 
			{
//				System.out.println("Weather");
				queryString += " AND category = ?";
			}
				
			query = jdbc.prepareStatement(queryString + " GROUP BY v.venue_name;");
			int counter = 1;
			
			if(filters.get("Capacity")) 
			{
				query.setInt(counter, r.getAudienceNumber());
				counter++;
			}
			
			if(filters.get("Type")) 
			{
				query.setString(counter, r.getType());
				counter++;
			}
			
			if(filters.get("Weather")) 
			{
				String category = r.getCategory();
				query.setString(counter, category.substring(0, 1).toUpperCase() + category.substring(1));
				counter++;
			}
			
			ResultSet resultSet = query.executeQuery();
			
			ObservableList<Venue> venues = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				Venue newVenue = new Venue();
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
				
				venues.add(newVenue);
			}
			
//			if(filters.get("TimeDate")) 
//			{
//				for(int i = venues.size()-1; i >=0; i++) 
//				{
//					
//					
//					if(Request.doesOverlap(r.getDate(), r.getTime(), r.getDuration(), , queryString, i))
//				}
//			}
			
			jdbc.close();
			query.close();
			
			return venues;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
