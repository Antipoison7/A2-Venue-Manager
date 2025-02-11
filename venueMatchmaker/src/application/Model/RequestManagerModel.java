package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import application.Model.ObjectClasses.Request;

public class RequestManagerModel extends JDBCHelper
{
	public boolean addNewRequest(Request r) 
	{
		boolean doesSucceed = false;
		
		try 
		{
			Connection jdbc = connectDB();
			
			PreparedStatement query;
			
			query = jdbc.prepareStatement("INSERT INTO requests (client_name, title, artist, date, time, duration, audience_number, type, category) VALUES (?,?,?,?,?,?,?,?,?);");
			
			query.setString(1, r.getClientName());
			query.setString(2, r.getTitle());
			query.setString(3, r.getArtist());
			query.setString(4, r.getDate());
			query.setString(5, r.getTime());
			query.setDouble(6, r.getDuration());
			query.setInt(7, r.getAudienceNumber());
			query.setString(8, r.getType());
			query.setString(9, r.getCategory());
			
			query.executeUpdate();
			
			
			query.close();
			
			jdbc.close();
			
			doesSucceed = true;
		}
		catch(Exception e) 
		{
			return false;
		}
		
		return doesSucceed;
	}
	
	public boolean isValidRequest(Request r) 
	{
		boolean isValid = true;
		
		if(r.getClientName().length() <= 0) 
		{
			System.out.println("Client name too short");
			isValid = false;
		}
		
		if(r.getTitle().length() <= 0) 
		{
			System.out.println("Title too short");
			isValid = false;
		}
		
		if(r.getArtist().length() <= 0) 
		{
			System.out.println("Artist name too short");
			isValid = false;
		}
		
		List<String> splitDate = Arrays.asList(r.getDate().strip().split("-"));
		
		if(splitDate.get(0).length() != 2) 
		{
			System.out.println("Invalid Days");
			isValid = false;
		}
		
		if(splitDate.get(1).length() != 2) 
		{
			System.out.println("Invalid Months");
			isValid = false;
		}
		
		if(splitDate.get(2).length() != 2) 
		{
			System.out.println("Invalid Years");
			isValid = false;
		}
		
		if(r.getTime().length() != 4) 
		{
			System.out.println("Invalid time");
			isValid = false;
		}
		
		
		
		if(Integer.parseInt(r.getTime().substring(0,2)) > 12) 
		{
			System.out.println("Super Time");
			isValid = false;
		}
		
		try //Yeah this is kinda gross but I don't have time to do a more elegant solution
		{
			Integer.parseInt(r.getTime().substring(0,1));
			Integer.parseInt(r.getTime().substring(1,2));
		}
		catch(NumberFormatException e) 
		{
			System.out.println("Invalid time, out of order");
			isValid = false;
		}
		
		if(r.getDuration() <= 0) 
		{
			System.out.println("Invalid duration");
			isValid = false;
		}
		
		if(r.getAudienceNumber() <= 0) 
		{
			System.out.println("Invalid audience number");
			isValid = false;
		}
		
		if(r.getType().length() <= 0) 
		{
			System.out.println("Invalid type");
			isValid = false;
		}
		
		if(r.getCategory().length() <= 0) 
		{
			System.out.println("Invalid category");
			isValid = false;
		}
		
		return isValid;
	}
	
	public boolean isDuplicateRequest(String client, String title, String artist, String date, String time)
	{
		boolean isDuplicate = false;
		
		try 
		{
			Connection jdbc = connectDB();
			
			Statement statement = jdbc.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT client_name, title, artist, date, time FROM requests;");
			
			while(rs.next()) 
			{
				if((rs.getString(1).equals(client))&&(rs.getString(2).equals(title))&&(rs.getString(3).equals(artist))&&(rs.getString(4).equals(date))&&(rs.getString(5).equals(time))) 
				{
					isDuplicate = true;
				}
			}
			
			statement.close();
			jdbc.close();
		}
		catch(Exception e) 
		{
			return true;
		}
		
		return isDuplicate;
	}
	
	public Request formatRequest(Request r) 
	{
		try 
		{
			Request newRequest = new Request(-1, r.getClientName(), r.getTitle(), r.getArtist(), r.getDate(), r.getTime(), r.getDuration(), r.getAudienceNumber(), r.getType(), r.getCategory());
			
			if(newRequest.getDate().length() != 8) 
			{
				List<String> splitDate = Arrays.asList(r.getDate().strip().split("-"));
				
				String newDate = splitDate.get(2) + "-" + splitDate.get(1) + "-";
				
				if(splitDate.get(0).length() == 4) 
				{
					newDate += splitDate.get(0).substring(2,4);
				}
				else 
				{
					newDate += splitDate.get(0);
				}
				
				r.setDate(newDate);
			}
			
			if(r.getTime().length() != 4) 
			{
				r.setTime(0 + r.getTime());
			}
			
			return newRequest;
		}
		catch(Exception e) 
		{
			return null;
		}
		
	}
}
