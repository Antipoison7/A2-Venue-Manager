package application.Model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.Venue;

public class BackupModel extends JDBCHelper{
	public int importVenues(File csv) 
	{
		int numberOfFailiures = 0;
		
		try 
		{
			Scanner scnr = new Scanner(csv);
			
			String currString = "";
			
			ArrayList<Venue> venues = new ArrayList<Venue>();
			
			scnr.nextLine();
			while(scnr.hasNextLine()) 
			{
				currString = scnr.nextLine();
				
					List<String> csvLine = Arrays.asList(currString.split(","));
					
					Venue currVenue = new Venue();
					
					currVenue.setName(csvLine.get(0).strip());
					currVenue.setCapacity(Integer.valueOf(csvLine.get(1).strip()));
					currVenue.setCategory(csvLine.get(3).strip());
					currVenue.setHirePrice(Double.valueOf(csvLine.get(4).strip()));
					
					List<String> suitable = Arrays.asList(csvLine.get(2).strip().split("; "));
					
					for(String s : suitable) 
					{
						currVenue.addToType(s);
					}
					
					venues.add(currVenue);
				
//					System.out.println(currVenue.toString());
			}
			
			scnr.close();
			
			NewVenueModel nvm = new NewVenueModel();
			
			//Removes Duplicates
			for(int i = venues.size()-1; i >=0; i--) 
			{
				if(nvm.isDuplicateVenuename(venues.get(i).getName())) 
				{
					venues.remove(i);
					numberOfFailiures++;
				}
			}
			
			try 
			{
				Connection jdbc = connectDB();
				
				for(Venue v : venues) 
				{
					PreparedStatement query;
					
					query = jdbc.prepareStatement("INSERT INTO venues (venue_name, hire_price, capacity, category, bookable) VALUES(?,?,?,?,1);");
					
					query.setString(1, v.getName());
					query.setDouble(2, v.getHirePrice());
					query.setInt(3, v.getCapacity());
					query.setString(4, v.getCategory());
					
					query.executeUpdate();
					
					Statement statement = jdbc.createStatement();
					
					ResultSet rs = statement.executeQuery("SELECT MAX(venue_id) FROM venues;");
					
					while(rs.next()) 
					{
						v.setId(rs.getInt(1)-1);							
					}
					
					rs.close();
					
					for(String s : v.getSuitableType()) 
					{
						PreparedStatement typeQuery;
						
						typeQuery = jdbc.prepareStatement("INSERT INTO venues_suitable (venue_id, event_type) VALUES (?,?);");
						typeQuery.setInt(1, v.getId());
						typeQuery.setString(2, s);
						
						typeQuery.executeUpdate();
						typeQuery.close();
					}
					
					query.close();
				}
				
				jdbc.close();
				
			}
			catch(Exception e) 
			{
				System.out.println(e);
				return -1;
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return -1;
		}
		
		return numberOfFailiures;
	}
	
	public int importRequests(File csv) 
	{
		int numberOfFailiures = 0;
		
		try 
		{
			Scanner scnr = new Scanner(csv);
			
			String currString = "";
			
			ArrayList<Request> requests = new ArrayList<Request>();
			
			scnr.nextLine();
			while(scnr.hasNextLine()) 
			{
				currString = scnr.nextLine();
				
				List<String> csvLine = Arrays.asList(currString.split(","));
				
				Request currRequest = new Request();
				
				ObjectDBInterface db = new ObjectDBInterface();
			
				db.doesClientExist(csvLine.get(0).strip());
				
				currRequest.setClientName(csvLine.get(0).strip());
				currRequest.setTitle(csvLine.get(1).strip());
				currRequest.setArtist(csvLine.get(2).strip());
				List<String> splitDate = Arrays.asList(csvLine.get(3).strip().split("-"));
				
				String date = "";
				
				if(splitDate.get(0).length() == 1) 
				{
					date += "0" + splitDate.get(0);
				}
				else 
				{
					date += splitDate.get(0);
				}
				
				date += "-";
				
				if(splitDate.get(1).length() == 1) 
				{
					date += "0" + splitDate.get(1);
				}
				else 
				{
					date += splitDate.get(1);
				}
				
				date += "-";
				
				if(splitDate.get(2).length() ==1) 
				{
					date += 0 + splitDate.get(2);
				}
				else if(splitDate.get(2).length() == 4) 
				{
					date += splitDate.get(2).substring(2, 4);
				}
				else 
				{
					date += splitDate.get(2);
				}
				
				currRequest.setDate(date);
				
				currRequest.setTime(csvLine.get(4).strip());
				currRequest.setDuration(Double.parseDouble(csvLine.get(5).strip()));
				currRequest.setAudienceNumber(Integer.parseInt(csvLine.get(6).strip()));
				currRequest.setType(csvLine.get(7).strip());
				currRequest.setCategory(csvLine.get(8).strip());
				
				requests.add(currRequest);
			
				System.out.println(currRequest.toString());
			}
			
			scnr.close();
			
			RequestManagerModel nrm = new RequestManagerModel();
			
			//Removes Duplicates
			for(int i = requests.size()-1; i >=0; i--) 
			{
				if(nrm.isDuplicateRequest(requests.get(i).getClientName(), requests.get(i).getTitle(), requests.get(i).getArtist(), requests.get(i).getDate(), requests.get(i).getTime())) 
				{
					requests.remove(i);
					numberOfFailiures++;
				}
			}
			
			try 
			{
				Connection jdbc = connectDB();
				
				for(Request r : requests) 
				{
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
				}
				
				jdbc.close();
				
			}
			catch(Exception e) 
			{
				System.out.println(e);
				return -1;
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
			return -1;
		}
		
		return numberOfFailiures;
	}
}
