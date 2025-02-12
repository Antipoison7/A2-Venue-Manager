/*
 * Class: TransactionBackup
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
 * - Requests
 * - Event Bookings
 * - Venues (+ Venues_suitable)
 * 
 */

package application.Model.ObjectClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class TransactionBackup implements Serializable {
	// Variables
	private ArrayList<Request> requests = new ArrayList<Request>();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<Venue> venues = new ArrayList<Venue>();

	// Getters and Setters
	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	public void addRequest(Request request) {
		requests.add(request);
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void addBooking(Booking booking) {
		bookings.add(booking);
	}

	public ArrayList<Venue> getVenues() {
		return venues;
	}

	public void setVenues(ArrayList<Venue> venues) {
		this.venues = venues;
	}
	
	public void addVenue(Venue venue) {
		venues.add(venue);
	}
}
