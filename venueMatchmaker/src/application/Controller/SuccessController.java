package application.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SuccessController {

    @FXML
    private Button closeErrorButton;

    @FXML
    private Label successBodyLabel;

    @FXML
    private Label successTitleLabel;
    
    public void updateText(String t, String b) 
	{
		successTitleLabel.setText(t);
		successBodyLabel.setText(b);
	}

    @FXML
    public void closeWindow(Event e) 
    {
    	System.out.println("Window Closed");
    	Stage stage = (Stage) closeErrorButton.getScene().getWindow();
    	stage.close();
    } 

}
