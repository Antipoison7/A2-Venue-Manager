package application.Controller;

import application.View.EmployeeManagerView;
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
    	
    }
}