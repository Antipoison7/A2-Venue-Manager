package application.Controller;

import application.Model.NewStaffMembers;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.User;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.DataSummaryView;
import application.View.EmployeeManagerView;
import application.View.ErrorGenerator;
import application.View.LoginView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateStaffProfileController {

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
    private Label title;
    
    @FXML
    private TextField fullName;

    @FXML
    private TextField password;

    @FXML
    private Button updateDetailsButton;

    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) title.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) title.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) title.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) title.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) title.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    	
    	Stage stage = (Stage) title.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) title.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
    @FXML
    public void logOut(ActionEvent e) 
    {
    	System.out.println("Log Out");
    	Stage stage = (Stage) title.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
    }
    
    @FXML
    public void updateDetails(ActionEvent e) 
    {
    	try 
    	{
    		NewStaffMembers staffAdded = new NewStaffMembers();
    		User updatedUser = new User(CurrentUser.getUser());
    		updatedUser.setRealName(fullName.getText());
    		updatedUser.setPassword(password.getText());
    		
    		if(staffAdded.isUserValid(updatedUser)) 
    		{
    			if(!staffAdded.updateStaffMember(updatedUser))
    			{
    				ErrorGenerator errorThrow = new ErrorGenerator();
    				
    				errorThrow.setErrorTitle("DB Error");
        	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
        	    	
        	    	errorThrow.throwError();
    			}
    			else 
    			{
    				LoginView lv = new LoginView();
    				lv.start((Stage) fullName.getScene().getWindow());
    			}
    		}
    		else 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    	    	
    	    	errorThrow.setErrorTitle("Entry Error");
    	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
    	    	
    	    	errorThrow.throwError();
    		}
    	}
    	catch(Exception f) 
    	{
    		System.out.println(f);
    	}	
    }
    
    public void initialize() 
    {
    	if(CurrentUser.getUser().getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);
    	}
    	
    	title.setText("Update Profile: " + CurrentUser.getUser().getUsername());
    	fullName.setText(CurrentUser.getUser().getRealName());
    	password.setText(CurrentUser.getUser().getPassword());
    }

}
