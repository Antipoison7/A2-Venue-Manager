package application.Controller;

import java.util.HashMap;

import application.Model.BookVenueModel;
import application.Model.ObjectDBInterface;
import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.Request;
import application.View.BookingManagerView;
import application.View.ErrorGenerator;
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
    		Request r = db.selectRequest(requestID);
    		Booking newBooking = new Booking(r);
    		
    		newBooking.setStaff(CurrentUser.getUser().getUsername());
    		newBooking.setCost(db.selectVenue(venueName).getHirePrice()*db.selectRequest(requestID).getDuration());
    		
    		if(groupBooking) 
    		{
    			newBooking.setCost(newBooking.getCost()*0.99);
    		}
    		
    		newBooking.setCommission(0.1);
    		
    		newBooking.setGroup(groupBooking);
    		
    		newBooking.setVenue(venueName);
    		
    		if(book.addBookingToVenue(newBooking)) 
    		{
    			if(book.removeRequest(requestID)) 
    			{
    				Stage stage = (Stage) buttonDiscard.getScene().getWindow();
    		    	
    		    	BookingManagerView view = new BookingManagerView();
    		    	view.openBookingManager(stage);
    			}
    			else 
    			{
    				ErrorGenerator errorThrow = new ErrorGenerator();
        			
        			errorThrow.setErrorTitle("DB Error");
        			errorThrow.setErrorBody("Something went wrong when removing the request, the booking has been added. Please contact IT if you believe this is a bug");
        			
        			errorThrow.throwError();
    			}
    		}
    		else 
    		{
    			ErrorGenerator errorThrow = new ErrorGenerator();
    			
    			errorThrow.setErrorTitle("DB Error");
    			errorThrow.setErrorBody("Something went wrong when adding the booking. Please contact IT if you believe this is a bug");
    			
    			errorThrow.throwError();
    		}
    	}
    	catch(Exception e) 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
			
			errorThrow.setErrorTitle("DB Error");
			errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
			
			errorThrow.throwError();
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
