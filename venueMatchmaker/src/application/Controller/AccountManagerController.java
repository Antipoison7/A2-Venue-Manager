package application.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AccountManagerController 
{

    @FXML
    private Button closeButton;

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

}