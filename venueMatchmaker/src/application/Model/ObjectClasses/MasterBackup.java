package application.Model.ObjectClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class MasterBackup implements Serializable {
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Client> clients = new ArrayList<Client>();

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
}
