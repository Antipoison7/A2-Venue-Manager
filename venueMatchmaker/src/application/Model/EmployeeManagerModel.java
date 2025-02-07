package application.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Model.ObjectClasses.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeManagerModel extends JDBCHelper{
	public ObservableList<User> getUsers()
	{
		try 
		{
			Connection jdbc =  connectDB();
			
			Statement statement = jdbc.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
			
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
}
