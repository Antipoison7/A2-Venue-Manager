package application.Controller;

import application.View.BookingManagerView;
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
	private TextField timeMinute;

	@FXML
	private TextField title;

	@FXML
	void createBooking(ActionEvent event) {

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
