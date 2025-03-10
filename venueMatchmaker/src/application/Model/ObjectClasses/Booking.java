package application.Model.ObjectClasses;


public class Booking extends Request implements java.io.Serializable{
	private String staff = "";
	private double cost = -1;
	private double commission = 0.0;
	private String venue = "";
	private boolean groupBooking = false;
	
	public Booking()
	{
		
	}
	
	public Booking(int requestID, String clientName, String title, String artist, String date, String time,
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
	
	public Booking(Request r) 
	{
		this.requestID = r.getRequestID();
		this.clientName = r.getClientName();
		this.title = r.getTitle();
		this.artist = r.getArtist();
		this.date = r.getDate();
		this.time = r.getTime();
		this.duration = r.getDuration();
		this.audienceNumber = r.getAudienceNumber();
		this.type = r.getType();
		this.category = r.getCategory();
	}
	
	public Booking(int requestID, String clientName, String title, String artist, String date, String time,
			double duration, int audienceNumber, String type, String category, String staff, double cost, double commission, String venue, boolean groupBooking) {
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
		this.staff = staff;
		this.cost = cost;
		this.commission = commission;
		this.venue = venue;
		this.groupBooking = groupBooking;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public boolean getGroup() 
	{
		return groupBooking;
	}
	
	public void setGroup(boolean group) 
	{
		groupBooking = group;
	}
	
	
}
