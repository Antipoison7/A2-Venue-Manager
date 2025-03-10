package application.Controller;

import application.Model.LoginHelper;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.User;
import application.View.BookingManagerView;
import application.View.ErrorGenerator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button unimplButton;

    @FXML
    private TextField usernameField;
    
    @FXML
    public void tryLogin(ActionEvent e) 
    {
    	try 
    	{
    		LoginHelper loginHelper = new LoginHelper();
    		
    		if(loginHelper.isLoginValid(usernameField.getText(), passwordField.getText())) 
    		{
    			System.out.println("Login Success");
    			
    			BookingManagerView manager = new BookingManagerView();
    			
    			User loginUser = new User();
    			
    			loginUser = loginHelper.getUserDetails(usernameField.getText());
    			
    			if(loginHelper.doesPassSecurity(usernameField.getText(), passwordField.getText(), 1))
    			{
    				loginUser.setSecurity(1);
    			}
    			
    			CurrentUser.setUser(loginUser);
    			
    			manager.openBookingManager((Stage) ((Node)e.getSource()).getScene().getWindow());
    		}
    		else 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    	    	
    	    	errorThrow.setErrorTitle("Invalid username or password");
    	    	errorThrow.setErrorBody("The entered username and or password is invalid and does not match any account in our database.");
    	    	
    	    	errorThrow.throwError();
    		}
    	}
    	catch(Exception f) 
    	{
    		System.out.println(f.toString());
    	}
    }
    
    @FXML
    public void resetPassword(Event e) 
    {
    	ErrorGenerator errorThrow = new ErrorGenerator();
    	
    	errorThrow.setErrorTitle("FeatureOutOfScope");
    	errorThrow.setErrorBody("This feature is not in scope of Assignment 2. It this was a full product it would be implemented however it is not a requirement for Assignment 2.");
    	
    	errorThrow.throwError();
    }
}
