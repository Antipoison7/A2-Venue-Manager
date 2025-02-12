package application.Controller;

import java.util.HashMap;

import application.Model.BookVenueModel;
import application.Model.ObjectDBInterface;
import application.Model.ObjectClasses.Request;
import application.View.BookingManagerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BookVenueController {
	
	private static String venueName;
	private static int requestID;
	private static boolean groupBooking;

    @FXML
    private Button bookVenue;

    @FXML
    private Button buttonDiscard;

    @FXML
    private Label compDoubleBooking;

    @FXML
    private Label compCapacity;

    @FXML
    private Label compEvent;

    @FXML
    private Label compScore;

    @FXML
    private Label compType;

    @FXML
    void bookVenue(ActionEvent event) 
    {
    	ObjectDBInterface db = new ObjectDBInterface();
    	BookVenueModel book = new BookVenueModel();
    	
    	try 
    	{
    		
    	}
    	catch(Exception e) 
    	{
    		
    	}
    }

    @FXML
    void discard(ActionEvent event) {
    	Stage stage = (Stage) buttonDiscard.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }

    public void initialize() 
    {
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	BookVenueModel book = new BookVenueModel();
    	
    	HashMap<String, Boolean> compare = book.calculateCompatibility(db.selectRequest(requestID), db.selectVenue(venueName));
    	
    	if(compare.get("Capacity")) 
    	{
    		compCapacity.setText("Enough Capacity");
    		compCapacity.setTextFill(Color.GREEN);
    	}
    	else 
    	{
    		compCapacity.setText("Not Enough Capacity");
    		compCapacity.setTextFill(Color.RED);
    	}
    	
    	if(compare.get("Type")) 
    	{
    		compType.setText("Perfect Conditions");
    		compType.setTextFill(Color.GREEN);
    	}
    	else 
    	{
    		compType.setText("Not Perfect Conditions");
    		compType.setTextFill(Color.RED);
    	}
    	
    	if(compare.get("Event")) 
    	{
    		compEvent.setText("Perfect Match");
    		compEvent.setTextFill(Color.GREEN);
    	}
    	else 
    	{
    		compEvent.setText("Not Match");
    		compEvent.setTextFill(Color.RED);
    	}
    	
    	if(compare.get("Double")) 
    	{
    		compDoubleBooking.setText("Not Double Booked");
    		compDoubleBooking.setTextFill(Color.GREEN);
    	}
    	else 
    	{
    		compDoubleBooking.setText("Double Booked");
    		compDoubleBooking.setTextFill(Color.RED);
    		bookVenue.setDisable(true);
    	}
    	
    	compScore.setText((book.getCompatibilityNumber(db.selectRequest(requestID), db.selectVenue(venueName))*25) + "%");
    	
//    	Request selectedRequest = db.selectRequest(ID);
    }
    
    public static void setVenueName(String name) 
    {
    	venueName = name;
    }
    
    public static void setRequestID(int id) 
    {
    	requestID = id;
    }
    
    public static void setGroupBooking(boolean group) 
    {
    	groupBooking = group;
    }
}
