package application.Controller;

import java.util.ArrayList;

import application.Model.AllEventsSearchModel;
import application.Model.AllVenuesSearchModel;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.Venue;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.DataSummaryView;
import application.View.DetailsEventView;
import application.View.DetailsVenueView;
import application.View.EmployeeManagerView;
import application.View.LoginView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AllEventsController {

    @FXML
    private MenuItem ddAllEvents;

    @FXML
    private MenuItem ddAllVenues;

    @FXML
    private MenuItem ddBackup;

    @FXML
    private MenuItem ddBookingManager;

    @FXML
    private MenuItem ddCustomise;

    @FXML
    private MenuItem ddEmployees;

    @FXML
    private MenuItem ddStats;
    
    @FXML
    private MenuItem ddLogout;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<Booking, String> tClientName;

    @FXML
    private TableColumn<Booking, Double> tCommission;

    @FXML
    private TableColumn<Booking, Double> tCost;

    @FXML
    private TableColumn<Booking, Integer> tID;

    @FXML
    private TableColumn<Booking, String> tStaffName;

    @FXML
    private TableColumn<Booking, String> tTitle;

    @FXML
    private TableColumn<Booking, String> tVenue;

    @FXML
    private TableView<Booking> table;

    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    	
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
    @FXML
    public void logOut(ActionEvent e) 
    {
    	System.out.println("Log Out");
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
    }
    
    
    @FXML
    public void updateTableSearch(ActionEvent e) 
    {
//    	System.out.println("Update");
    	
    	if(searchBox.getText().length() == 0) 
    	{
    		tID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        	tClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        	tStaffName.setCellValueFactory(new PropertyValueFactory<>("staff"));
        	tCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        	tCommission.setCellValueFactory(new PropertyValueFactory<>("commission"));
        	tTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        	tVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));  
        	
        	    
    		TableListGenerator mm = new TableListGenerator();
    		table.setItems(mm.getEvents());
    	}
    	else 
    	{
    		AllEventsSearchModel mm = new AllEventsSearchModel();
    		
    		tID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        	tClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        	tStaffName.setCellValueFactory(new PropertyValueFactory<>("staff"));
        	tCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        	tCommission.setCellValueFactory(new PropertyValueFactory<>("commission"));
        	tTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        	tVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));  
        	
    		table.setItems(mm.returnSearch(mm.getBookingDumpArrayList(), searchBox.getText()));
    	}
    }
    
    public void initialize()
    {
    	if(CurrentUser.getUser().getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);
    	}
    	
    	tID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
    	tClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
    	tStaffName.setCellValueFactory(new PropertyValueFactory<>("staff"));
    	tCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    	tCommission.setCellValueFactory(new PropertyValueFactory<>("commission"));
    	tTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    	tVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));    	
    	    
		TableListGenerator mm = new TableListGenerator();
		table.setItems(mm.getEvents());
		
		table.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				try {
	            int index = table.getSelectionModel().getSelectedIndex();
	            Booking booking = table.getItems().get(index);
	            DetailsEventController.setID(booking.getRequestID());
				}
				catch(Exception e) {}
	        }
			
	        if(event.getButton().equals(MouseButton.PRIMARY)){
	            if(event.getClickCount() == 2){
	            	try {
	                System.out.println("Double clicked");
	                
	                int index = table.getSelectionModel().getSelectedIndex();
		            Booking booking = table.getItems().get(index);
	               
	                DetailsEventView dev = new DetailsEventView();
	                
	                DetailsEventController.setID(booking.getRequestID());
	                
	                dev.openNewEventDetails();
	            	}
					catch(Exception e) {}
	                
	            }
	        }
	    });
    }
}