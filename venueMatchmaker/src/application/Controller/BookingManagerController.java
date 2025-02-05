package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;

public class BookingManagerController 
{

	//Add new venue to table button
	@FXML
    private Button addVenue;

	@FXML
	private Button newBooking;
	
    @FXML
    private Button autoMatch;

    @FXML
    private Button bookVenue;

    @FXML
    private CheckBox bookingDiscount;

    @FXML
    private CheckBox checkCapacity;

    @FXML
    private CheckBox checkGig;

    @FXML
    private CheckBox checkInOut;

    @FXML
    private CheckBox checkTime;
    
    //Dropdown Menu Items
    @FXML
    private MenuItem ddCustomise;

    @FXML
    private MenuItem ddBackup;

    @FXML
    private MenuItem ddEmployees;

    @FXML
    private MenuItem ddStats;

    @FXML
    private Button filterVenues;

    @FXML
    private Button moreRequestDetails;

    @FXML
    private Button moreVenueDetails;
    
    @FXML
    public void addNewVenue(ActionEvent e) 
    {
    	System.out.println("Add New Venue Button");
    }
    
    @FXML
    public void addNewBooking(ActionEvent e) 
    {
    	System.out.println("Add New Booking Button");
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    }
    
    @FXML
    public void filterVenues(ActionEvent e) 
    {
    	System.out.println("Filter Venues");
    }
    
    @FXML
    public void autoMatch(ActionEvent e) 
    {
    	System.out.println("Auto Match");
    }
    
    @FXML
    public void showMoreVenueDetails(ActionEvent e) 
    {
    	System.out.println("Show More Venue Details");
    }
    
    @FXML
    public void bookVenue(ActionEvent e) 
    {
    	System.out.println("Book Venue");
    }

}
