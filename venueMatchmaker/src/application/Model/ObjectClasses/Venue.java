package application.Model.ObjectClasses;

import java.util.ArrayList;

public class Venue {

	protected String name = "";
	protected double hirePrice = -1;
	protected int capacity = -1;
	protected String category = "";
	protected boolean bookable = true;
	protected ArrayList<String> suitableType = new ArrayList<String>();
	
	public Venue() 
	{
		
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHirePrice() {
		return hirePrice;
	}

	public void setHirePrice(double hirePrice) {
		this.hirePrice = hirePrice;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isBookable() {
		return bookable;
	}

	public void setBookable(boolean bookable) {
		this.bookable = bookable;
	}

	public ArrayList<String> getSuitableType() {
		return suitableType;
	}

	public void setSuitableType(ArrayList<String> suitableType) {
		this.suitableType = suitableType;
	}
	
	public void addToType(String type) 
	{
		suitableType.add(type);
	}
	
	@Override
	public String toString() 
	{
		String toString = "";
		
		toString += "Name: " + name + "\n";
		toString += "Hire Price: " + hirePrice + "\n";
		toString += "Capacity: " + capacity + "\n";
		toString += "Category: " + category + "\n";
		toString += "Bookable: " + bookable + "\n";
		toString += "Suitable Event Types \n";
		
		for(String s : suitableType) 
		{
			toString += "- " + s + "\n";
		}
		
		return toString;
	}

}
