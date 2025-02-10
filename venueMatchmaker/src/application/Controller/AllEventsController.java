package application.Controller;

import application.Model.AllVenuesSearchModel;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.Booking;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.EmployeeManagerView;
import application.View.NewManagerView;
import application.View.NewUserView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public void openNewUser() 
    {
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	NewUserView view = new NewUserView();
    	view.openNewUserView(stage);
    }
    
    @FXML
    public void openNewManager() 
    {
    	Stage stage = (Stage) searchButton.getScene().getWindow();
    	NewManagerView view = new NewManagerView();
    	view.openNewUserView(stage);
    }
    
    //Dropdown Methods
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
    public void updateTableSearch(ActionEvent e) 
    {
//    	System.out.println("Update");
    	
    	if(searchBox.getText().length() == 0) 
    	{
    		tClientName.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    		tCommission.setCellValueFactory(new PropertyValueFactory<>("category"));

    		tCost.setCellValueFactory(new PropertyValueFactory<>("id"));

    		tID.setCellValueFactory(new PropertyValueFactory<>("name"));

    		tStaffName.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

    		tTitle.setCellValueFactory(new PropertyValueFactory<>("type"));
    		
    		tVenue.setCellValueFactory(new PropertyValueFactory<>("type"));
        	
        	    
    		TableListGenerator mm = new TableListGenerator();
//    		table.setItems(mm.getVenueDump());
    	}
    	else 
    	{
    		AllVenuesSearchModel mm = new AllVenuesSearchModel();
    		
    		tClientName.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    		tCommission.setCellValueFactory(new PropertyValueFactory<>("category"));

    		tCost.setCellValueFactory(new PropertyValueFactory<>("id"));

    		tID.setCellValueFactory(new PropertyValueFactory<>("name"));

    		tStaffName.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

    		tTitle.setCellValueFactory(new PropertyValueFactory<>("type"));
    		
    		tVenue.setCellValueFactory(new PropertyValueFactory<>("type"));
        	
    		
//    		tableData = mm.getVenueDumpArrayList();
    		
//    		table.setItems(mm.returnSearch(tableData, searchBox.getText()));
    	}
    }
    
    public void initialize()
    {
    	tClientName.setCellValueFactory(new PropertyValueFactory<>("capacity"));

		tCommission.setCellValueFactory(new PropertyValueFactory<>("category"));

		tCost.setCellValueFactory(new PropertyValueFactory<>("id"));

		tID.setCellValueFactory(new PropertyValueFactory<>("name"));

		tStaffName.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

		tTitle.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		tVenue.setCellValueFactory(new PropertyValueFactory<>("type"));
    	
    	    
		TableListGenerator mm = new TableListGenerator();
//		table.setItems(mm.getVenueDump());
    }
}