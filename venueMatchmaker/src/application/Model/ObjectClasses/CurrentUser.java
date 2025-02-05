package application.Model.ObjectClasses;

public class CurrentUser { // Singleton
	private static User loggedUser = null;
	
	public static void setUser(User user) 
	{
		loggedUser = user;
	}
	
	public static User getUser() 
	{
		return loggedUser;
	}
}
