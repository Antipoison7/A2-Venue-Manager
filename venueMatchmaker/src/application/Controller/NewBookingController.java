package application.Controller;

import java.util.Arrays;
import java.util.List;

import application.Model.ObjectDBInterface;
import application.Model.RequestManagerModel;
import application.Model.ObjectClasses.Request;
import application.View.BookingManagerView;
import application.View.ErrorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewBookingController {

	@FXML
	private TextField artists;

	@FXML
	private TextField audienceNumber;

	@FXML
	private ComboBox<String> bookingCondition;

	@FXML
	private ComboBox<String> bookingType;

	@FXML
	private ComboBox<String> amPM;
	
	@FXML
	private Button buttonDiscard;

	@FXML
	private TextField clientName;

	@FXML
	private Button createBooking;

	@FXML
	private DatePicker date;

	@FXML
	private TextField duration;

	@FXML
	private TextField timeHour;

	@FXML
	private TextField title;

	@FXML
	void createBooking(ActionEvent event) 
	{
		try 
		{
			Request booking = new Request();
			RequestManagerModel manager = new RequestManagerModel();
			
			booking.setClientName(clientName.getText());
			booking.setTitle(title.getText());
			booking.setArtist(artists.getText());
			
			List<String> splitDate = Arrays.asList(date.getValue().toString().strip().split("-"));
			
			String newDate = splitDate.get(2) + "-" + splitDate.get(1) + "-" + (splitDate.get(0).substring(2,4));
			
			booking.setDate(newDate);
			booking.setTime((timeHour.getText() + amPM.getValue()).toLowerCase());
			booking.setDuration(Double.parseDouble(duration.getText()));
			booking.setAudienceNumber(Integer.parseInt(audienceNumber.getText()));
			booking.setType(bookingType.getValue());
			booking.setCategory(bookingCondition.getValue());
			
			if(manager.isValidRequest(booking)) 
			{
				if(!manager.isDuplicateRequest(booking.getClientName(), booking.getTitle(), booking.getArtist(), booking.getDate(), booking.getTime())) 
				{
					if(manager.addNewRequest(booking)) 
					{
						ObjectDBInterface db = new ObjectDBInterface();
						db.doesClientExist(clientName.getText());
						System.out.println("Open Booking Manager");
				    	
				    	Stage stage = (Stage) createBooking.getScene().getWindow();
				    	
				    	BookingManagerView view = new BookingManagerView();
				    	view.openBookingManager(stage);
					}
					else 
					{
						ErrorGenerator errorThrow = new ErrorGenerator();
        				
        				errorThrow.setErrorTitle("DB Error");
            	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
            	    	
            	    	errorThrow.throwError();
					}
					
				}
				else 
				{
					ErrorGenerator errorThrow = new ErrorGenerator();
			    	
			    	errorThrow.setErrorTitle("Duplicate Booking");
			    	errorThrow.setErrorBody("The booking matches one or more requests in the database.");
			    	
			    	errorThrow.throwError();
				}
			}
			else 
			{
				ErrorGenerator errorThrow = new ErrorGenerator();
		    	
		    	errorThrow.setErrorTitle("Invalid Booking");
		    	errorThrow.setErrorBody("One or more fields were invalid when creating the booking, please review and try again.");
		    	
		    	errorThrow.throwError();
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
			
			ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("Invalid Booking");
	    	errorThrow.setErrorBody("One or more fields caused an error when creating the booking, please review and try again.");
	    	
	    	errorThrow.throwError();
		}
	}

	@FXML
	void discard(ActionEvent event) {
		Stage stage = (Stage) buttonDiscard.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
	}
	
	@FXML
	public void initialize() 
	{
		ObservableList<String> data = FXCollections.observableArrayList("Gig","Disco","Live Concert","Large Live Concert","Festival");
    	bookingType.setItems(data);
    	
    	ObservableList<String> dataCondition = FXCollections.observableArrayList("Indoor","Convertible","Outdoor");
    	bookingCondition.setItems(dataCondition);
    	
    	ObservableList<String> dataAmPm = FXCollections.observableArrayList("AM", "PM");
    	amPM.setItems(dataAmPm);
	}

}
