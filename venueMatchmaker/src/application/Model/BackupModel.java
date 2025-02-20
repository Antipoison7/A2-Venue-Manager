package application.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Client;
import application.Model.ObjectClasses.MasterBackup;
import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.TransactionBackup;
import application.Model.ObjectClasses.User;
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
					
					for(String s : v.getSuitableType()) 
					{
						PreparedStatement typeQuery;
						
						typeQuery = jdbc.prepareStatement("INSERT INTO venues_suitable (venue_id, event_type) VALUES (?,?);");
						typeQuery.setString(1, v.getName());
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
	
	public boolean exportTransactionBackup() 
	{
		try 
		{
			TransactionBackup backup = new TransactionBackup();
			
			//Connect to DB
			Connection jdbc =  connectDB();
			Statement statement = jdbc.createStatement();
			
			//Get Requests
			ResultSet resultSet = statement.executeQuery("SELECT * FROM requests;");
			
			while (resultSet.next()) {
				Request req = new Request();

				req.setRequestID(resultSet.getInt(1));
				req.setClientName(resultSet.getString(2));
				req.setTitle(resultSet.getString(3));
				req.setArtist(resultSet.getString(4));
				req.setDate(resultSet.getString(5));
				req.setTime(resultSet.getString(6));
				req.setDuration(resultSet.getDouble(7));
				req.setAudienceNumber(resultSet.getInt(8));
				req.setType(resultSet.getString(9));
				req.setCategory(resultSet.getString(10));

				backup.addRequest(req);

			}
			
			//Get Bookings
			resultSet = statement.executeQuery("SELECT * FROM event_bookings;");
			
			while (resultSet.next()) {
				Booking book = new Booking();

				book.setRequestID(resultSet.getInt(1));
				book.setClientName(resultSet.getString(2));
				book.setStaff(resultSet.getString(3));
				book.setCost(resultSet.getDouble(4));
				book.setCommission(resultSet.getDouble(5));
				book.setTitle(resultSet.getString(6));
				book.setArtist(resultSet.getString(7));
				book.setDate(resultSet.getString(8));
				book.setTime(resultSet.getString(9));
				book.setDuration(resultSet.getDouble(10));
				book.setAudienceNumber(resultSet.getInt(11));
				book.setType(resultSet.getString(12));
				book.setGroup(resultSet.getBoolean(13));
				book.setCategory(resultSet.getString(14));
				book.setVenue(resultSet.getString(15));
				
				backup.addBooking(book);
			}
			
			//Get Venues
			resultSet = statement.executeQuery("SELECT * FROM venues;");
			
			while (resultSet.next()) {
				Venue ven = new Venue();

				ven.setName(resultSet.getString(1));
				ven.setHirePrice(resultSet.getDouble(2));
				ven.setCapacity(resultSet.getInt(3));
				ven.setCategory(resultSet.getString(4));
				ven.setBookable(resultSet.getBoolean(5));

				PreparedStatement query = jdbc.prepareStatement("SELECT * FROM venues_suitable WHERE venue_id = ?;");
				query.setString(1, ven.getName());
				
				ResultSet suitableEvents = query.executeQuery();
				
				while (suitableEvents.next()) {
					ven.addToType(suitableEvents.getString(2));
				}
				
				query.close();
				
				backup.addVenue(ven);
			}
			
			jdbc.close();
			statement.close();
			
			FileOutputStream file = new FileOutputStream("transactiondata.lmvm");
	        ObjectOutputStream out = new ObjectOutputStream(file);
	        
	        out.writeObject(backup);
	        
	        out.close();
	        file.close();
	        
	        return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean exportMasterBackup() 
	{
		try 
		{
			MasterBackup backup = new MasterBackup();
			
			//Connect to DB
			Connection jdbc =  connectDB();
			Statement statement = jdbc.createStatement();
			
			//Get Requests
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
			
			//Get Staff / Users
			while(resultSet.next()) 
			{
				User user = new User();
				
				user.setUsername(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setRealName(resultSet.getString(3));
				user.setSecurity(resultSet.getInt(4));
				user.setActive(resultSet.getBoolean(5));
				
				backup.addUser(user);
			}
			
			resultSet = statement.executeQuery("SELECT * FROM clients;");
			
			while (resultSet.next()) {
				Client client = new Client();
				
				client.setName(resultSet.getString(1));
				
				backup.addClient(client);
			}
			
			jdbc.close();
			statement.close();
			
			FileOutputStream file = new FileOutputStream("masterdata.lmvm");
	        ObjectOutputStream out = new ObjectOutputStream(file);
	        
	        out.writeObject(backup);
	        
	        out.close();
	        file.close();
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	//Yes, I know this is bad, however I am running out of time.
	public int importTransactionBackup(File transactionFile) 
	{
		int numberOfUpdates = 0;
		try 
		{
			FileInputStream file = new FileInputStream(transactionFile);
            ObjectInputStream in = new ObjectInputStream(file);
            
            TransactionBackup backup = (TransactionBackup)in.readObject();
            
            Connection jdbc =  connectDB();
            
            //Load Venues
            PreparedStatement query = jdbc.prepareStatement("INSERT OR REPLACE INTO venues (venue_name, hire_price, capacity, category, bookable) VALUES(?,?,?,?,?);");
            
			for (Venue v : backup.getVenues()) 
			{
				query.setString(1, v.getName());
				query.setDouble(2, v.getHirePrice());
				query.setInt(3, v.getCapacity());
				query.setString(4, v.getCategory());
				query.setBoolean(5, v.isBookable());
				
				numberOfUpdates += query.executeUpdate();
				
				PreparedStatement suitableType = jdbc.prepareStatement("INSERT OR REPLACE INTO venues_suitable (venue_id, event_type) VALUES (?,?);");
				
				for (String s : v.getSuitableType()) {
					suitableType.setString(1, v.getName());
					suitableType.setString(2, s);

					numberOfUpdates += suitableType.executeUpdate();
				}
			}
			
            //Load Requests
            query = jdbc.prepareStatement("INSERT OR REPLACE INTO requests (client_name, title, artist, date, time, duration, audience_number, type, category) VALUES (?,?,?,?,?,?,?,?,?);");
            
            for(Request r : backup.getRequests()) 
            {
            	RequestManagerModel nrm = new RequestManagerModel();
            	
            	if(!nrm.isDuplicateRequest(r.getClientName(), r.getTitle(), r.getArtist(), r.getDate(), r.getTime())) 
            	{
            		PreparedStatement clientQuery = jdbc.prepareStatement("INSERT INTO clients(name) SELECT ? WHERE NOT EXISTS (SELECT name FROM clients WHERE name = ?);");
                	
                	clientQuery.setString(1, r.getClientName());
                	clientQuery.setString(2, r.getClientName());
                	
                	numberOfUpdates += clientQuery.executeUpdate();
                	
                	query.setString(1, r.getClientName());
                	query.setString(2, r.getTitle());
                	query.setString(3, r.getArtist());
                	query.setString(4, r.getDate());
                	query.setString(5, r.getTime());
                	query.setDouble(6, r.getDuration());
                	query.setInt(7, r.getAudienceNumber());
                	query.setString(8, r.getType());
                	query.setString(9, r.getCategory());
                	
                	numberOfUpdates += query.executeUpdate();
            	}
            }
            
            //Load Bookings
            
            query = jdbc.prepareStatement("INSERT OR REPLACE INTO event_bookings (client_name, staff_username, cost, commission, title, artist, date, start_time, duration, audience_number, type, group_booking, category, venue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            
			for(Booking b : backup.getBookings()) 
			{
				PreparedStatement clientQuery = jdbc.prepareStatement("INSERT INTO clients(name) SELECT ? WHERE NOT EXISTS (SELECT name FROM clients WHERE name = ?);");
            	
            	clientQuery.setString(1, b.getClientName());
            	clientQuery.setString(2, b.getClientName());
            	
            	numberOfUpdates += clientQuery.executeUpdate();
            	
            	clientQuery = jdbc.prepareStatement("INSERT INTO users(username, password, real_name, security, active) SELECT ?,'samplePassword','sampleRealName',0,0 WHERE NOT EXISTS (SELECT username FROM users WHERE username = ?);");
            	
            	clientQuery.setString(1, b.getStaff());
            	clientQuery.setString(2, b.getStaff());
            	
            	numberOfUpdates += clientQuery.executeUpdate();
				
				query.setString(1, b.getClientName());
				query.setString(2, b.getStaff());
				query.setDouble(3, b.getCost());
				query.setDouble(4, b.getCommission());
				query.setString(5, b.getTitle());
				query.setString(6, b.getArtist());
				query.setString(7, b.getDate());
				query.setString(8, b.getTime());
				query.setDouble(9, b.getDuration());
				query.setInt(10, b.getAudienceNumber());
				query.setString(11, b.getType());
				query.setBoolean(12, b.getGroup());
				query.setString(13, b.getCategory());
				query.setString(14, b.getVenue());				

				numberOfUpdates += query.executeUpdate();
			}
            
            in.close();
            jdbc.close();
            query.close();
            
			return numberOfUpdates;
		}
		catch(Exception e)
        {
            return -1;
        }
	}
	
	public int importMasterBackup(File masterFile) 
	{
		int numberOfUpdates = 0;
		try 
		{
			FileInputStream file = new FileInputStream(masterFile);
            ObjectInputStream in = new ObjectInputStream(file);
            
            MasterBackup backup = (MasterBackup)in.readObject();
            
            Connection jdbc =  connectDB();
            
            PreparedStatement query = jdbc.prepareStatement("INSERT OR REPLACE INTO users (username, password, real_name, security, active) VALUES (?,?,?,?,?);");
            
			for (User u : backup.getUsers()) {

				query.setString(1, u.getUsername());
				query.setString(2, u.getPassword());
				query.setString(3, u.getRealName());
				query.setInt(4, u.getSecurity());
				query.setBoolean(5, u.getActive());

				numberOfUpdates += query.executeUpdate();
			}
			
			query = jdbc.prepareStatement("INSERT OR REPLACE INTO clients (name) VALUES (?);");
			
			for (Client c : backup.getClients()) {
				
				query.setString(1, c.getName());

				numberOfUpdates += query.executeUpdate();
			}
			
			in.close();
			jdbc.close();
			query.close();
			
			return numberOfUpdates;
		}
		catch(Exception e)
        {
            return -1;
        }
	}
}
