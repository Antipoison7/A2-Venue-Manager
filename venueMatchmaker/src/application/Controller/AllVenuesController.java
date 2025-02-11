package application.Controller;

import java.util.ArrayList;

import application.Model.AllVenuesSearchModel;
import application.Model.ObjectDBInterface;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.Venue;
import application.Model.ObjectClasses.VenueDump;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.DataSummaryView;
import application.View.DetailsVenueView;
import application.View.EmployeeManagerView;
import application.View.ErrorGenerator;
import application.View.LoginView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AllVenuesController {

	private ArrayList<VenueDump> tableData = new ArrayList<VenueDump>();
	
	private String selectedVenueName = "";
	
	@FXML
    private MenuItem ddAllVenues;
	
	@FXML
    private MenuItem ddAllEvents;

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
    private TableColumn<VenueDump, Integer> tCapacity;

    @FXML
    private TableColumn<VenueDump, String> tCategory;

    @FXML
    private TableColumn<VenueDump, String> tName;

    @FXML
    private TableColumn<VenueDump, Double> tPrice;

    @FXML
    private TableColumn<VenueDump, String> tSuitable;
    
    @FXML
    private TableColumn<VenueDump, Boolean> tActive;

    @FXML
    private TableView<VenueDump> table;
    
    @FXML
    private Label selected;
    
	
    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) selected.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) selected.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) selected.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) selected.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) selected.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    	
    	Stage stage = (Stage) selected.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) selected.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
    @FXML
    public void logOut(ActionEvent e) 
    {
    	System.out.println("Log Out");
    	Stage stage = (Stage) selected.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
    }
    
    @FXML
    public void toggleDisabled(ActionEvent e) 
    {
    	if(selectedVenueName.equals("")) 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("No Venue Selected");
	    	errorThrow.setErrorBody("Please select a valid venue and try again (Click on table row)");
	    	
	    	errorThrow.throwError();
    	}
    	else 
    	{
    		ObjectDBInterface db = new ObjectDBInterface();
    		
    		db.toggleVenueWhere(selectedVenueName);
    		
    		AllVenuesView venue = new AllVenuesView();
    		venue.openAllVenues((Stage) ((Node)e.getSource()).getScene().getWindow());
    	}
    }
    
    @FXML
    public void updateTableSearch(ActionEvent e) 
    {
//    	System.out.println("Update");
    	
    	if(searchBox.getText().length() == 0) 
    	{
    		tCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    		tCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

    		tName.setCellValueFactory(new PropertyValueFactory<>("name"));

    		tPrice.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

    		tSuitable.setCellValueFactory(new PropertyValueFactory<>("type"));
    		
    		tActive.setCellValueFactory(new PropertyValueFactory<>("bookable"));
        	
        	    
    		TableListGenerator mm = new TableListGenerator();
    		table.setItems(mm.getVenueDump());
    	}
    	else 
    	{
    		AllVenuesSearchModel mm = new AllVenuesSearchModel();
    		
    		tCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    		tCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

    		tName.setCellValueFactory(new PropertyValueFactory<>("name"));

    		tPrice.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

    		tSuitable.setCellValueFactory(new PropertyValueFactory<>("type"));
    		
    		tActive.setCellValueFactory(new PropertyValueFactory<>("bookable"));
        	
    		
    		tableData = mm.getVenueDumpArrayList();
    		
    		table.setItems(mm.returnSearch(tableData, searchBox.getText()));
    	}
    }
    
    public void initialize()
    {
    	
    	if(CurrentUser.getUser().getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);
    	}
    	
		tCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

		tCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		tName.setCellValueFactory(new PropertyValueFactory<>("name"));

		tPrice.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

		tSuitable.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		tActive.setCellValueFactory(new PropertyValueFactory<>("bookable"));
    	
    	    
		TableListGenerator mm = new TableListGenerator();
		table.setItems(mm.getVenueDump());
		
		table.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
	            int index = table.getSelectionModel().getSelectedIndex();
	            Venue venue = table.getItems().get(index);
	            selectedVenueName = venue.getName();
	            selected.setText("Selected ID : " + selectedVenueName);
	        }
			
	        if(event.getButton().equals(MouseButton.PRIMARY)){
	            if(event.getClickCount() == 2){
	                System.out.println("Double clicked");
	                
	                int index = table.getSelectionModel().getSelectedIndex();
		            Venue venue = table.getItems().get(index);
	               
	                DetailsVenueView dvv = new DetailsVenueView();
	                
	                DetailsVenueController.setName(venue.getName());
	                
	                dvv.openNewVenueDetails();
	                
	            }
	        }
	    });
    }
}
