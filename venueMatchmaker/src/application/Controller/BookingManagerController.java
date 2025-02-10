package application.Controller;

import application.Model.TableListGenerator;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.User;
import application.Model.ObjectClasses.Venue;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.EmployeeManagerView;
import application.View.NewBookingView;
import application.View.NewVenueView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private MenuItem ddAllVenues;
    
    @FXML
    private MenuItem ddAllEvents;
    
    @FXML
    private MenuItem ddBookingManager;
    
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
    private TableColumn<Venue, Integer> vCapac;

    @FXML
    private TableColumn<Venue, String> vCompat; //Indoors vs outdoors

    @FXML
    private TableColumn<Venue, Integer> vID;

    @FXML
    private TableColumn<Venue, String> vName;

    @FXML
    private TableView<Venue> venueTable;
    
    @FXML
    public void addNewVenue(ActionEvent e) 
    {
    	NewVenueView window = new NewVenueView();
    	window.openVenueWindow((Stage) newBooking.getScene().getWindow());
    }
    
    @FXML
    public void addNewBooking(ActionEvent e) 
    {
    	System.out.println("Add New Booking Button");
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	NewBookingView view = new NewBookingView();
    	view.openBookingWindow(stage);
    }
    
    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
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
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
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
    
    
    public void initialize()
    {
    	User selectedUser = CurrentUser.getUser();
    	if(selectedUser.getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);

    	}
    	
    	 vCapac.setCellValueFactory(new PropertyValueFactory<>("capacity"));

 	    vCompat.setCellValueFactory(new PropertyValueFactory<>("category"));

 	    vID.setCellValueFactory(new PropertyValueFactory<>("id"));

 	    vName.setCellValueFactory(new PropertyValueFactory<>("name"));
 	    
 	    TableListGenerator mm = new TableListGenerator();
 	    venueTable.setItems(mm.getVenues());
    }

}
