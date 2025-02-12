package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.VenueDump;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AllEventsSearchModel extends JDBCHelper{
	public ArrayList<Booking> getBookingDumpArrayList()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM event_bookings");
			
			ArrayList<Booking> bookings = new ArrayList<Booking>(); 
			
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
	
	public ObservableList<Booking> returnSearch(ArrayList<Booking> bookingList, String searchString) 
	{
//		System.out.println("Ping");
		return bookingList.stream().filter(e -> e.getTitle().toLowerCase().contains(searchString.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
}
