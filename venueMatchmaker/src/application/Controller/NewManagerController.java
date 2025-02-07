package application.Controller;

import application.Model.NewStaffMembers;
import application.Model.ObjectClasses.User;
import application.View.EmployeeManagerView;
import application.View.ErrorGenerator;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewManagerController {
	@FXML
    private Button backButton;

    @FXML
    private Button createAccount;

    @FXML
    private TextField newFullName;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newUsername;

    @FXML
    private TextField secretCEOCode;
    
    @FXML
    public void back(Event e) 
    {
    	Stage stage = (Stage) createAccount.getScene().getWindow();
    	EmployeeManagerView view = new EmployeeManagerView();
    	view.openManagerView(stage);
    }
    
    @FXML
    public void addManager(Event e) 
    {
    	try 
    	{
    		NewStaffMembers staff = new NewStaffMembers();
    		
    		User selectedUser = new User();
    		
    		int secretCode = Integer.parseInt(secretCEOCode.getText());
    		
    		selectedUser.setUsername(newUsername.getText());
    		selectedUser.setPassword(newPassword.getText());
    		selectedUser.setRealName(newFullName.getText());
    		selectedUser.setSecurity(1);
    		
    		if(staff.isManagerValid(selectedUser, secretCode)) 
    		{
    			if(!staff.addNewStaff(selectedUser, 1)) 
    			{
    				ErrorGenerator errorThrow = new ErrorGenerator();
    				
    				errorThrow.setErrorTitle("DB Error");
        	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
        	    	
        	    	errorThrow.throwError();
    			}
    			else 
    			{
    				Stage stage = (Stage) createAccount.getScene().getWindow();
    		    	EmployeeManagerView view = new EmployeeManagerView();
    		    	view.openManagerView(stage);
    			}
    			
    		}
    		else 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    	    	
    	    	errorThrow.setErrorTitle("Entry Error");
    	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy. Thing");
    	    	
    	    	errorThrow.throwError();
    		}
    	}
    	catch(Exception f) 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("Something Went Wrong");
	    	errorThrow.setErrorBody(f.toString());
	    	
	    	errorThrow.throwError();
    	}
    	
    	
    }
}