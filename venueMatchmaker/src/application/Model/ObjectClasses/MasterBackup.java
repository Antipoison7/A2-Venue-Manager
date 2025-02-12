/*
 * Class: MasterBackup
 * 
 * V1.0
 * 
 * February 2025
 * 
 * Connor Orders - s4096467
 * 
 * This class serves to store data in a serialized format that can be output to a file and then loaded in later.
 * 
 * This class stores
 * - Users
 * - Clients
 * 
 */

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
	
	public void addUser(User user) {
		users.add(user);
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public void addClient(Client client) {
		clients.add(client);
	}
}
