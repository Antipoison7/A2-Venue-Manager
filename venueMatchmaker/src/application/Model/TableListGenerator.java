package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

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
				newVenue.setId(resultSet.getInt(1));
				newVenue.setName(resultSet.getString(2));
				newVenue.setHirePrice(resultSet.getDouble(3));
				newVenue.setCapacity(resultSet.getInt(4));
				newVenue.setCategory(resultSet.getString(5));
				
				if(resultSet.getInt(6) == 1) 
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
				newVenue.setId(resultSet.getInt(1));
				newVenue.setName(resultSet.getString(2));
				newVenue.setHirePrice(resultSet.getDouble(3));
				newVenue.setCapacity(resultSet.getInt(4));
				newVenue.setCategory(resultSet.getString(5));
				
				if(resultSet.getInt(6) == 1) 
				{
					newVenue.setBookable(true);
				}
				else 
				{
					newVenue.setBookable(false);
				}
				
				PreparedStatement query;
				query = jdbc.prepareStatement("SELECT event_type FROM venues_suitable WHERE venue_id = ?;");
				query.setInt(1, resultSet.getInt(1));
				
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
	
	public ObservableList<String> getVenueTypes(int i)
	{
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			query = jdbc.prepareStatement("SELECT event_type FROM venues_suitable WHERE venue_id = ?;");
			query.setInt(1, i);
			
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
			String queryString = "SELECT v.venue_id, v.venue_name, v.hire_price, v.capacity, v.category, v.bookable, s.event_type FROM venues as v JOIN venues_suitable as s ON v.venue_id = s.venue_id WHERE bookable = 1";
			
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
				
//			System.out.println(queryString + "GROUP BY v.venue_id;");
			query = jdbc.prepareStatement(queryString + " GROUP BY v.venue_id;");
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
				query.setString(counter, r.getCategory());
				counter++;
			}
			
			ResultSet resultSet = query.executeQuery();
			
			ObservableList<Venue> venues = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				Venue newVenue = new Venue();
				newVenue.setId(resultSet.getInt(1));
				newVenue.setName(resultSet.getString(2));
				newVenue.setHirePrice(resultSet.getDouble(3));
				newVenue.setCapacity(resultSet.getInt(4));
				newVenue.setCategory(resultSet.getString(5));
				
				if(resultSet.getInt(6) == 1) 
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
