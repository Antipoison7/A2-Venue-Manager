package application.Controller;

import java.io.File;

import application.Model.BackupModel;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.EmployeeManagerView;
import application.View.ErrorGenerator;
import application.View.NewManagerView;
import application.View.NewUserView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BackupManagerController {

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
    private Button exportMasterButton;

    @FXML
    private Button exportTransactionButton;

    @FXML
    private Button importMasterButton;

    @FXML
    private Button importRequestsButton;

    @FXML
    private Button importTransactionButton;

    @FXML
    private Button importVenuesButton;

    @FXML
    private GridPane managerBackupGrid;

    @FXML
    private GridPane staffBackupgrid;

    @FXML
    void exportMasterdata(ActionEvent event) {

    }

    @FXML
    void exportTransactiondata(ActionEvent event) {

    }

    @FXML
    void importMasterdata(ActionEvent event) {

    }
    
    @FXML 
    void importTransactiondata(ActionEvent event) {

    }

    
    
    @FXML //CSV
    void importRequests(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
    	
    	ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.csv", "*.CSV");
    	
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	try 
    	{
    		File selectedFile = fileChooser.showOpenDialog((Stage) staffBackupgrid.getScene().getWindow());
    		
    		BackupModel backup = new BackupModel();
    		
    		int dupes = backup.importRequests(selectedFile);
    		
    		if(dupes == -1) 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("Something went wrong with importing");
    	    	errorThrow.setErrorBody("Check to see if your .csv file is the correct one, contains the correct formatting or is missing a header / title row");
    	    	
    	    	errorThrow.throwError();
    		}
    		else if(dupes != 0) 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("Duplicates");
    	    	errorThrow.setErrorBody(dupes + " Number of Duplicate Entries");
    	    	
    	    	errorThrow.throwError();
    		}
    	}
    	catch(Exception e) 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
			
			errorThrow.setErrorTitle("DB Error");
	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
	    	
	    	errorThrow.throwError();
    	}
    }
    
    @FXML //CSV
    void importVenues(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	
    	ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.csv", "*.CSV");
    	
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	try 
    	{
    		File selectedFile = fileChooser.showOpenDialog((Stage) staffBackupgrid.getScene().getWindow());
    		
    		BackupModel backup = new BackupModel();
    		
    		int dupes = backup.importVenues(selectedFile);
    		
    		if(dupes == -1) 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("Something went wrong with importing");
    	    	errorThrow.setErrorBody("Check to see if your .csv file is the correct one, contains the correct formatting or is missing a header / title row");
    	    	
    	    	errorThrow.throwError();
    		}
    		else if(dupes != 0) 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("Duplicates");
    	    	errorThrow.setErrorBody(dupes + " Number of Duplicate Entries");
    	    	
    	    	errorThrow.throwError();
    		}
    	}
    	catch(Exception e) 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
			
			errorThrow.setErrorTitle("DB Error");
	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
	    	
	    	errorThrow.throwError();
    	}
    }

    
  //Dropdown Methods

    @FXML
    public void openNewUser() 
    {
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	NewUserView view = new NewUserView();
    	view.openNewUserView(stage);
    }
    
    @FXML
    public void openNewManager() 
    {
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	NewManagerView view = new NewManagerView();
    	view.openNewUserView(stage);
    }
    
    
    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	
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
    	Stage stage = (Stage) staffBackupgrid.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }

}
