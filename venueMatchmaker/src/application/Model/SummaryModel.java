package application.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
