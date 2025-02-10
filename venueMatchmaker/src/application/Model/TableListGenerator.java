package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			ResultSet resultSet = statement.executeQuery("SELECT * FROM venues");
			
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
}
