package application.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ErrorController {

	@FXML
	private Button closeErrorButton;
	
	@FXML
	private Label errorTitle;
	
	@FXML
	private Label errorBody;
	
	public void updateText(String t, String b) 
	{
		errorTitle.setText(t);
		errorBody.setText(b);
	}
    
	@FXML
    public void closeWindow(Event e) 
    {
    	System.out.println("Window Closed");
    	Stage stage = (Stage) closeErrorButton.getScene().getWindow();
    	stage.close();
    } 
}
