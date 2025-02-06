package application.Model.ObjectClasses;

import java.util.ArrayList;

public class Venue {

	private int id;
	private String name;
	private double hirePrice;
	private int capacity;
	private String category;
	private boolean bookable = true;
	private ArrayList<String> suitableType;
	
	public Venue() 
	{
		
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

}
