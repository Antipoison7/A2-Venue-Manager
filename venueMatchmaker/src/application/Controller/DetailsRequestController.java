package application.Controller;

import application.Model.ObjectDBInterface;
import application.Model.ObjectClasses.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailsRequestController {
	
	private static int ID;
	
	@FXML
    private Label artist;

    @FXML
    private Label category;

    @FXML
    private Label client;

    @FXML
    private Button closeButton;

    @FXML
    private Label date;

    @FXML
    private Label duration;

    @FXML
    private Label expectedAudience;

    @FXML
    private Label idAndTitle;

    @FXML
    private Label startTime;

    @FXML
    private Label type;

    @FXML
    public void closeWindow(ActionEvent event) {
    	System.out.println("Window Closed");
    	Stage stage = (Stage) type.getScene().getWindow();
    	stage.close();
    }

    
    public void initialize() 
    {
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	Request selectedRequest = db.selectRequest(ID);
    	
    	
    	//Set Title
    	idAndTitle.setText("#" + selectedRequest.getRequestID() + " | " + selectedRequest.getTitle());
    	//Client
    	client.setText(selectedRequest.getClientName());
    	//Artists
    	artist.setText(selectedRequest.getArtist());
    	//Date
    	date.setText(selectedRequest.getDate());
    	//Start Time
    	startTime.setText(selectedRequest.getTime());
    	//Duration
    	duration.setText(selectedRequest.getDuration() + " Hours");
    	//Expected Audience
    	expectedAudience.setText(""+selectedRequest.getAudienceNumber());
    	//Type
    	type.setText(selectedRequest.getType());
    	//Category
    	category.setText(selectedRequest.getCategory());
    }


	public static void setID(int id) {
		ID = id;
	}
    
}
