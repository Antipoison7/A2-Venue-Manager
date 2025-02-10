package application.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class NewRequestModel extends JDBCHelper{

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
}
