package application.Controller;

import application.Model.ObjectDBInterface;
import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailsEventController {
	
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
    private Label commission;

    @FXML
    private Label cost;

    @FXML
    private Label date;

    @FXML
    private Label duration;

    @FXML
    private Label expectedAudience;

    @FXML
    private Label groupBooking;

    @FXML
    private Label idAndTitle;

    @FXML
    private Label staff;

    @FXML
    private Label startTime;

    @FXML
    private Label type;

    @FXML
    private Label venue;

    @FXML
    void closeWindow(ActionEvent event) {
    	System.out.println("Window Closed");
    	Stage stage = (Stage) type.getScene().getWindow();
    	stage.close();
    }
    
    public void initialize() 
    {
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	Booking selectedRequest= db.selectBooking(ID);
    	
    	
    	//Set Title
    	idAndTitle.setText("#" + selectedRequest.getRequestID() + " | " + selectedRequest.getTitle());
    	//Client
    	client.setText(selectedRequest.getClientName());
    	//Artists
    	artist.setText(selectedRequest.getArtist());
    	//Cost
    	cost.setText("$"+selectedRequest.getCost());
    	//Commission
    	commission.setText("$"+(selectedRequest.getCost()*selectedRequest.getCommission()));
    	//Venue
    	venue.setText(selectedRequest.getVenue());
    	//Staff
    	staff.setText(selectedRequest.getStaff());
    	//Group Booking
    	groupBooking.setText("" + selectedRequest.getGroup());
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
