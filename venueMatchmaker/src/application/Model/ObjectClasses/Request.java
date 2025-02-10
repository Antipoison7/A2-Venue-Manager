package application.Model.ObjectClasses;

import java.time.LocalDateTime;

public class Request {
	private int requestID = -1;
	private String clientName = "";
	private String title = "";
	private String artist = "";
	private LocalDateTime dateTime;
	private String date = "";
	private String time = "";
	private double duration = -1;
	private int audienceNumber = -1;
	private String type = "";
	private String category = "";

	public Request() 
	{
		
	}
	
	public Request(int requestID, String clientName, String title, String artist, LocalDateTime dateTime, String date, String time,
			double duration, int audienceNumber, String type, String category) {
		this.requestID = requestID;
		this.clientName = clientName;
		this.title = title;
		this.artist = artist;
		this.date = date;
		this.time = time;
		this.dateTime = dateTime;
		this.duration = duration;
		this.audienceNumber = audienceNumber;
		this.type = type;
		this.category = category;
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime date) {
		this.dateTime = date;
	}
	
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
			returnString += "Request ID: " + requestID;
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
