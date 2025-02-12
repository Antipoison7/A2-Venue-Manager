package application.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import application.Model.ObjectClasses.BarchartResult;
import application.Model.ObjectClasses.SummaryPerClient;
import application.Model.ObjectClasses.SummaryPerJob;
import application.Model.ObjectClasses.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SummaryModel extends JDBCHelper
{
	public ObservableList<SummaryPerJob> getCommPerJob()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT event_id, staff_username, cost, (cost*commission) as commission FROM event_bookings;");
			
			ObservableList<SummaryPerJob> jobs = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				SummaryPerJob newJob = new SummaryPerJob();
				newJob.setJobID(resultSet.getInt(1));
				newJob.setStaff(resultSet.getString(2));
				newJob.setOverallCost(resultSet.getDouble(3));
				newJob.setCommission(resultSet.getDouble(4));
				
				jobs.add(newJob);
			}
			
			jdbc.close();
			statement.close();
			
			return jobs;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public ObservableList<SummaryPerClient> getCommPerClient()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT client_name, SUM(cost) as total_cost, SUM(cost*commission) as total_commission FROM event_bookings GROUP BY client_name;");
			
			ObservableList<SummaryPerClient> jobs = FXCollections.observableArrayList();
			
			while(resultSet.next()) 
			{
				SummaryPerClient newClient = new SummaryPerClient();
				newClient.setClient(resultSet.getString(1));
				newClient.setCost(resultSet.getDouble(2));
				newClient.setCommission(resultSet.getDouble(3));
				
				jobs.add(newClient);
			}
			
			jdbc.close();
			statement.close();
			
			return jobs;
		}
		catch(Exception e) 
		{
			return null;
		}
	}
	
	public String getTotalCommissions() 
	{
		String commissions = "";
		
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(cost*commission) as total_commission, SUM(cost) as total_cost FROM event_bookings;");
			
			while(resultSet.next()) 
			{
				commissions = "$" + resultSet.getDouble(1) + " Commissions | $" + resultSet.getDouble(2) + " Cost";
			}
			
			jdbc.close();
			statement.close();
			
			return commissions;
		}
		catch(Exception e) 
		{
			return commissions;
		}
	}
	
	public ArrayList<BarchartResult> getBarchart()
	{
		ArrayList<BarchartResult> charts = new ArrayList<BarchartResult>();
		
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT title, cost, (cost*commission) as commission FROM event_bookings;;");
			
			while(resultSet.next()) 
			{
				
				charts.add(new BarchartResult(resultSet.getString(1), resultSet.getDouble(2), resultSet.getDouble(3)));
			}
			
			jdbc.close();
			statement.close();
			
			return charts;
		}
		catch(Exception e) 
		{
			return charts;
		}
	}
	
	public HashMap<String, Double> getPieChart()
	{
		HashMap<String, Double> slices = new HashMap<String, Double>();
		
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT date, start_time, duration FROM event_bookings;");
			
			long earliestEpoch = -1;
			long latestEpoch = -1;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy ha");
			
			while(resultSet.next()) 
			{
				long epoch = LocalDateTime.parse((resultSet.getString(1) + " " + resultSet.getString(2)), formatter).atZone(java.time.ZoneId.systemDefault()).toEpochSecond();
				if(earliestEpoch == -1) 
				{
					earliestEpoch = epoch;
				}
				else if(epoch < earliestEpoch)
				{
					earliestEpoch = epoch;
				}
				
				if(latestEpoch == -1) 
				{
					latestEpoch = epoch + (3600 * resultSet.getInt(3));
				}
				else if((epoch + (3600 * resultSet.getInt(3))) > latestEpoch) 
				{
					latestEpoch = epoch + (3600 * resultSet.getInt(3));
				}
			}
			
			
			resultSet = statement.executeQuery("SELECT venue FROM event_bookings GROUP BY(venue);");
			
			ArrayList<String> venuesBooked = new ArrayList<String>();
			HashMap<String, Long> sums = new HashMap<String, Long>();
			
			while(resultSet.next()) 
			{
				venuesBooked.add(resultSet.getString(1));
			}
			
			for(String v : venuesBooked) 
			{
				resultSet = statement.executeQuery("SELECT venue, date, start_time, duration FROM event_bookings WHERE venue = '" + v + "';");
				
				long venueSum = 0;
				
				while(resultSet.next()) 
				{
					venueSum = venueSum + (resultSet.getLong(4)*3600);
				}
				
				sums.put(v,venueSum);
			}
			
			
			long durationEpoch = (latestEpoch - earliestEpoch);
			
			
			for(String v : venuesBooked) 
			{
				slices.put(v, (((double) sums.get(v)/durationEpoch) * 100.0));
			}
			
			jdbc.close();
			statement.close();
			
			return slices;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return slices;
		}
	}
}
