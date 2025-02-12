package application.Model.ObjectClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request implements java.io.Serializable{
	protected int requestID = -1;
	protected String clientName = "";
	protected String title = "";
	protected String artist = "";
	protected String date = "";
	protected String time = "";
	protected double duration = -1;
	protected int audienceNumber = -1;
	protected String type = "";
	protected String category = "";

	public Request() 
	{
		
	}
	
	public Request(int requestID, String clientName, String title, String artist, String date, String time,
			double duration, int audienceNumber, String type, String category) {
		this.requestID = requestID;
		this.clientName = clientName;
		this.title = title;
		this.artist = artist;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.audienceNumber = audienceNumber;
		this.type = type;
		this.category = category;
	}

	//Static method that returns true if the bookings have a clash and false if they do not overlap.
	public static boolean doesOverlap(String date1, String time1, double duration1, String date2, String time2, double duration2)
	{
		boolean doesOverlap = false;
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy ha");

        LocalDateTime start1 = LocalDateTime.parse((date1 + " " + time1), formatter);
        LocalDateTime start2 = LocalDateTime.parse((date2 + " " + time2), formatter);
        
        LocalDateTime end1 = start1.plusHours((long)Math.ceil(duration1));
        LocalDateTime end2 = start2.plusHours((long)Math.ceil(duration2));

        if((start1.isBefore(end2)) && (start2.isBefore(end1))) 
        {
        	doesOverlap = true;
        }

		return doesOverlap;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

//	public double getDateTimeUT() 
//	{
//		Local
//		
//		return dateTime;
//	}
	
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	public String getDate() 
	{
		return date;
	}
	
	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getAudienceNumber() {
		return audienceNumber;
	}

	public void setAudienceNumber(int audienceNumber) {
		this.audienceNumber = audienceNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() 
	{
		String returnString = "";
		
		try 
		{
			returnString += "Request ID: " + requestID + "\n";
			returnString += "Client Name: " + clientName + "\n";
			returnString += "Event Title: " + title + "\n";
			returnString += "Artist/s: " + artist + "\n";
			returnString += "Date: " + date + "\n";
			returnString += "Time: " + time + "\n";
			returnString += "Duration: " + duration + "\n";
			returnString += "Audience Number: " + audienceNumber + "\n";
			returnString += "Type: " + type + "\n";
			returnString += "Category: " + category;
			
			return returnString;
		}
		catch(Exception e) 
		{
			return "Error???????";
		}
	}

}
