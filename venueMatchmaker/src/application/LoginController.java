package application;

import javafx.event.Event;
import javafx.fxml.FXML;
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
    public void tryLogin(Event e) 
    {
    	System.out.println("Login Button Pressed");
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
