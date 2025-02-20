package application.Controller;

import java.io.File;

import application.Model.BackupModel;
import application.Model.ObjectClasses.CurrentUser;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.DataSummaryView;
import application.View.EmployeeManagerView;
import application.View.ErrorGenerator;
import application.View.LoginView;
import application.View.SuccessGenerator;
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
    private MenuItem ddLogout;

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
    	BackupModel backup = new BackupModel();
    	backup.exportMasterBackup();
    }

    @FXML
    void exportTransactiondata(ActionEvent event) {
    	BackupModel backup = new BackupModel();
    	backup.exportTransactionBackup();
    }

    @FXML
    void importMasterdata(ActionEvent event) {
    	BackupModel backup = new BackupModel();
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.lmvm", "*.LMVM");
    	
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	try 
    	{
    		File selectedFile = fileChooser.showOpenDialog((Stage) staffBackupgrid.getScene().getWindow());
    		
    		int dupes = backup.importMasterBackup(selectedFile);
    		
    		if(dupes == -1) 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("Something went wrong with importing");
    	    	errorThrow.setErrorBody("Check to see if your .lmvm file is the correct one.");
    	    	
    	    	errorThrow.throwError();
    		}
    		else if(dupes != 0) 
    		{
    			SuccessGenerator successThrow = new SuccessGenerator();
    			
    			successThrow.setSuccessTitle("Updates");
    	    	successThrow.setSuccessBody(dupes + " Rows Updated");
    	    	
    	    	successThrow.throwSuccess();
    		}
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    @FXML 
    void importTransactiondata(ActionEvent event) {
    	BackupModel backup = new BackupModel();
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.lmvm", "*.LMVM");
    	
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	try 
    	{
    		File selectedFile = fileChooser.showOpenDialog((Stage) staffBackupgrid.getScene().getWindow());
    		
    		int dupes = backup.importTransactionBackup(selectedFile);
    		
    		if(dupes == -1) 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("Something went wrong with importing");
    	    	errorThrow.setErrorBody("Check to see if your .lmvm file is the correct one.");
    	    	
    	    	errorThrow.throwError();
    		}
    		else if(dupes != 0) 
    		{
    			SuccessGenerator successThrow = new SuccessGenerator();
    			
    			successThrow.setSuccessTitle("Updates");
    	    	successThrow.setSuccessBody(dupes + " Rows Updated");
    	    	
    	    	successThrow.throwSuccess();
    		}
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
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
    		else 
    		{
    			SuccessGenerator successThrow = new SuccessGenerator();
    			
    			successThrow.setSuccessTitle("Requests Imported");
    	    	successThrow.setSuccessBody(dupes + " Duplicates");
    	    	
    	    	successThrow.throwSuccess();
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
    		else 
    		{
    			SuccessGenerator successThrow = new SuccessGenerator();
    			
    			successThrow.setSuccessTitle("Requests Imported");
    	    	successThrow.setSuccessBody(dupes + " Duplicates");
    	    	
    	    	successThrow.throwSuccess();
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
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    	
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
    @FXML
    public void logOut(ActionEvent e) 
    {
    	System.out.println("Log Out");
    	Stage stage = (Stage) importVenuesButton.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
    }
    
    public void initialize() 
    {
    	if(CurrentUser.getUser().getSecurity() <= 0) 
    	{
    		managerBackupGrid.setDisable(true);
    		
    		ddEmployees.setVisible(false);

		    ddStats.setVisible(false);
    	}
    }

}
