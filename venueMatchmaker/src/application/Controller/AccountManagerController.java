package application.Controller;

import application.View.BookingManagerView;
import application.View.EmployeeManagerView;
import application.View.NewManagerView;
import application.View.NewUserView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountManagerController 
{
	@FXML
    private MenuItem ddBookingManager;
	
	@FXML
    private MenuItem ddBackup;

    @FXML
    private MenuItem ddCustomise;

    @FXML
    private MenuItem ddEmployees;

    @FXML
    private MenuItem ddStats;
	
    @FXML
    private Button deleteStaff;

    @FXML
    private Button managerTemplate;

    @FXML
    private Label selectedUserDelete;

    @FXML
    private Label selectedUserUpdate;

    @FXML
    private Button staffTemplate;

    //Button that uses the text to update the selected staff member
    @FXML
    private Button updateButton;

    @FXML
    private TextField updateFullName;

    @FXML
    private PasswordField updatePassword;

    @FXML
    private TextField updateUsername;
    
    
    @FXML
    public void openNewUser() 
    {
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	NewUserView view = new NewUserView();
    	view.openNewUserView(stage);
    }
    
    @FXML
    public void openNewManager() 
    {
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	NewManagerView view = new NewManagerView();
    	view.openNewUserView(stage);
    }
    
    //Dropdown Methods
    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) deleteStaff.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
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
    	Stage stage = (Stage) deleteStaff.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }

}