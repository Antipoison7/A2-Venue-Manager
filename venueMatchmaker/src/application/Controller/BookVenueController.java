package application.Controller;

import java.util.HashMap;

import application.Model.BookVenueModel;
import application.Model.ObjectDBInterface;
import application.Model.ObjectClasses.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BookVenueController {
	
	private static String venueName;
	private static int requestID;

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
    void bookVenue(ActionEvent event) {

    }

    @FXML
    void discard(ActionEvent event) {

    }

    public void initialize() 
    {
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	BookVenueModel book = new BookVenueModel();
    	
    	HashMap<String, Boolean> compare = book.calculateCompatibility(db.selectRequest(requestID), db.selectVenueNoType(venueName));
    	
    	if(compare.get("Capacity")) 
    	{
    		compCapacity.setText("Enough Capacity");
    	}
    	else 
    	{
    		compCapacity.setText("Not Enough Capacity");
    	}
    	
    	if(compare.get("Type")) 
    	{
    		compType.setText("Perfect Conditions");
    	}
    	else 
    	{
    		compType.setText("Not Perfect Conditions");
    	}
    	
    	if(compare.get("Event")) 
    	{
    		compEvent.setText("Perfect Match");
    	}
    	else 
    	{
    		compEvent.setText("Not Match");
    	}
    	
    	if(compare.get("Double")) 
    	{
    		compDoubleBooking.setText("Not Double Booked");
    	}
    	else 
    	{
    		compDoubleBooking.setText("Double Booked");
    	}
    	
    	compScore.setText("" + (book.getCompatibilityNumber(db.selectRequest(requestID), db.selectVenueNoType(venueName))*25));
    	
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
}
