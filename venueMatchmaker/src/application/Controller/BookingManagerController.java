package application.Controller;

import application.Model.ObjectDBInterface;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.Request;
import application.Model.ObjectClasses.User;
import application.Model.ObjectClasses.Venue;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.DataSummaryView;
import application.View.DetailsVenueView;
import application.View.EmployeeManagerView;
import application.View.ErrorGenerator;
import application.View.LoginView;
import application.View.NewBookingView;
import application.View.NewVenueView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookingManagerController 
{
	private int selectedVenueID = -1;
	
	@FXML
    private Label bookingTitle;
	
	@FXML
    private Label venueTitle;

	//Add new venue to table button
	@FXML
    private Button addVenue;
	
	@FXML
    private Button deleteVenueButton;

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
    private MenuItem ddLogout;

    @FXML
    private Button filterVenues;

    @FXML
    private Button moreRequestDetails;

    @FXML
    private Button moreVenueDetails;
    
    
    //Requests Table
    @FXML
    private TableColumn<Request, String> rArtist;

    @FXML
    private TableColumn<Request, String> rClient;

    @FXML
    private TableColumn<Request, Integer> rID;

    @FXML
    private TableColumn<Request, String> rTitle;

    @FXML
    private TableView<Request> requestTable;
    

    //Venues Table
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
    public void deleteVenue(ActionEvent event) {
    	System.out.println("Deleted ID");
    	
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	if(selectedVenueID >= 0) 
    	{
    		db.deleteVenueWhere(selectedVenueID);
    		
    		BookingManagerView manager = new BookingManagerView();
    		manager.openBookingManager((Stage) ((Node)event.getSource()).getScene().getWindow());
    	}
    	else 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("No Venue Selected");
	    	errorThrow.setErrorBody("Please select a valid venue and try again (Click on table row)");
	    	
	    	errorThrow.throwError();
    	}
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
    	
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
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
    public void logOut(ActionEvent e) 
    {
    	System.out.println("Log Out");
    	Stage stage = (Stage) newBooking.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
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
    	
    	if(CurrentUser.getUser().getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);
    	}
    	
    	TableListGenerator mm = new TableListGenerator();
    	
    	//Venue Table Updater
    	 vCapac.setCellValueFactory(new PropertyValueFactory<>("capacity"));

 	    vCompat.setCellValueFactory(new PropertyValueFactory<>("category"));

 	    vID.setCellValueFactory(new PropertyValueFactory<>("id"));

 	    vName.setCellValueFactory(new PropertyValueFactory<>("name"));
 	    
 	    venueTable.setItems(mm.getVenues());
 	    
 	    
 	    //Request Table Updater
 	    rArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));

	    rClient.setCellValueFactory(new PropertyValueFactory<>("clientName"));

	    rID.setCellValueFactory(new PropertyValueFactory<>("requestID"));

	    rTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
	    
	    requestTable.setItems(mm.getRequests());
	    
	    
	    venueTable.setOnMouseClicked((MouseEvent event) -> {
	        if (event.getButton().equals(MouseButton.PRIMARY)) {
	            int index = venueTable.getSelectionModel().getSelectedIndex();
	            Venue venue = venueTable.getItems().get(index);
	            selectedVenueID = venue.getId();
	            venueTitle.setText("Venues | Selected: " + selectedVenueID);
	        }
	        
	        if(event.getButton().equals(MouseButton.PRIMARY)){
	            if(event.getClickCount() == 2){
	                System.out.println("Double clicked");
	                
	                int index = venueTable.getSelectionModel().getSelectedIndex();
		            Venue venue = venueTable.getItems().get(index);
	               
	                DetailsVenueView dvv = new DetailsVenueView();
	                
	                DetailsVenueController.setID(venue.getId());
	                
	                dvv.openNewVenueDetails();
	                
	            }
	        }
	    });
    }

}
