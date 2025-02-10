package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

import application.Model.ObjectClasses.VenueDump;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AllVenuesSearchModel extends JDBCHelper{
	public ArrayList<VenueDump> getVenueDumpArrayList()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM venues");
			
			ArrayList<VenueDump> venues = new ArrayList<VenueDump>();
			
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
	
	public ObservableList<VenueDump> returnSearch(ArrayList<VenueDump> VenueList, String searchString) 
	{
//		System.out.println("Ping");
		return VenueList.stream().filter(e -> e.getName().toLowerCase().contains(searchString.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
}
