package application.Controller;

import application.Model.LoginHelper;
import application.Model.NewStaffMembers;
import application.Model.TableListGenerator;
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
import application.View.NewManagerView;
import application.View.NewUserView;
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

public class AccountManagerController 
{
	private String selectedAccountUsername = "";
	
	@FXML
	private MenuItem ddAllVenues;
	 
	@FXML
	private MenuItem ddAllEvents;
	
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
    private MenuItem ddLogout;
	
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
    private TextField updateSecurity;
    
    @FXML
    private TextField updateCEOCode;
    
    
    @FXML
    private TableColumn<User, String> tFullName;

    @FXML
    private TableColumn<User, Integer> tSecurity;

    @FXML
    private TableColumn<User, String> tUsername;

    @FXML
    private TableView<User> table;
    
    @FXML
    public void openBookingManager(ActionEvent e) 
    {
//    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
//    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
//    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
//    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
//    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
//    	System.out.println("Open Manager Stats");
    	
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
//    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
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
    
    @FXML
    public void logOut(ActionEvent e) 
    {
//    	System.out.println("Log Out");
    	Stage stage = (Stage) staffTemplate.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
    }
    
    @FXML
    void updateStaff(ActionEvent event) 
    {
    	LoginHelper log = new LoginHelper();
    	
    	if(log.doesAccountExist(selectedAccountUsername)) 
    	{
    		try 
        	{
        		User u = new User();
        		u.setUsername(selectedAccountUsername);
        		u.setRealName(updateFullName.getText());
        		u.setSecurity(Integer.parseInt(updateSecurity.getText()));
        		
        		NewStaffMembers db = new NewStaffMembers();
        		
        		if(!db.updateStaffMemberCheck(u,Integer.parseInt( updateCEOCode.getText()))) 
        		{
        			ErrorGenerator errorThrow = new ErrorGenerator();
        	    	
        	    	errorThrow.setErrorTitle("Invalid Data");
        	    	errorThrow.setErrorBody("Something went wrong when updating the user data, check to see that the secuirty level is valid, the CEO code is valid and/or the name field is filled out");	
        	    	
        	    	errorThrow.throwError();
        		}
        		else 
        		{
        			EmployeeManagerView manager = new EmployeeManagerView();
            		manager.openManagerView((Stage) ((Node)event.getSource()).getScene().getWindow());
        		}
        		
        	}
        	catch(Exception e) 
        	{
        		ErrorGenerator errorThrow = new ErrorGenerator();
    	    	
    	    	errorThrow.setErrorTitle("Update Error");
    	    	errorThrow.setErrorBody("Something went wrong when updating the user data, check to see if any fields are blank or if the CEO code is invalid.");
    	    	
    	    	errorThrow.throwError();
        	}
    	}
    	else 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("User Selection Error");
	    	errorThrow.setErrorBody("Selected user does not exist in the database, please select someone who is registered.");
	    	
	    	errorThrow.throwError();
    	}
    	
    	
    }
    
    @FXML
    void deleteStaff(ActionEvent event) 
    {
    	LoginHelper log = new LoginHelper();
    	
    	if(log.doesAccountExist(selectedAccountUsername)) 
    	{
    		NewStaffMembers db = new NewStaffMembers();
    		if(!db.deleteStaffMember(selectedAccountUsername))
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
				
				errorThrow.setErrorTitle("DB Error");
    	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
    	    	
    	    	errorThrow.throwError();
    		}
    		else 
    		{
    			EmployeeManagerView manager = new EmployeeManagerView();
        		manager.openManagerView((Stage) ((Node)event.getSource()).getScene().getWindow());
    		}
    	}
    	else 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("User Selection Error");
	    	errorThrow.setErrorBody("Selected user does not exist in the database, please select someone who is registered.");
	    	
	    	errorThrow.throwError();
    	}
    }
    
    
    public void initialize()
    {
    	if(CurrentUser.getUser().getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);
    	}
    	
    	    tFullName.setCellValueFactory(new PropertyValueFactory<>("realName"));

    	    tSecurity.setCellValueFactory(new PropertyValueFactory<>("security"));

    	    tUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    	    
    	    TableListGenerator mm = new TableListGenerator();
    	    table.setItems(mm.getUsers());
    	    
    	    table.setOnMouseClicked((MouseEvent event) -> {
    	        if (event.getButton().equals(MouseButton.PRIMARY)) {
    	        	try 
    	        	{
    	        		int index = table.getSelectionModel().getSelectedIndex();
        	            User user = table.getItems().get(index);
        	            selectedAccountUsername = user.getUsername();
        	            selectedUserDelete.setText("Selected: " + selectedAccountUsername);
        	            selectedUserUpdate.setText("Selected: " + selectedAccountUsername);
        	            updateFullName.setText(user.getRealName());
        	            updateSecurity.setText("" + user.getSecurity());
    	        	}
    	        	catch(Exception e) {}
    	        }
    	    });
    }

}