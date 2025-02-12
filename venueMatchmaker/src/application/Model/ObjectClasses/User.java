package application.Model.ObjectClasses;

public class User implements java.io.Serializable{
	private String username;
	private String password;
	private String realName;
	private int security;
	
	public User() 
	{
		
	}
	
	public User(User u) 
	{
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.realName = u.getRealName();
		this.security = u.getSecurity();
	}

	public User(int security) {
		this.security = security;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getSecurity() {
		return security;
	}

	public void setSecurity(int security) {
		this.security = security;
	}

}
