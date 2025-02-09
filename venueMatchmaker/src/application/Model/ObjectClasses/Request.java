package application.Model.ObjectClasses;

import java.time.LocalDate;
import java.time.LocalTime;

public class Request {
	private int requestID;
	private String clientName;
	private String title;
	private String artist;
	private LocalDate date;
	private LocalTime time;
	private Double duration;
	private int audienceNumber;
	private String type;
	private String category;

	public Request() 
	{
		
	}
	
	public Request(int requestID, String clientName, String title, String artist, LocalDate date, LocalTime time,
			Double duration, int audienceNumber, String type, String category) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
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

}
