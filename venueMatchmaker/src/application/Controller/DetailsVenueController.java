package application.Controller;

import application.Model.ObjectDBInterface;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.Booking;
import application.Model.ObjectClasses.Venue;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DetailsVenueController {
	
	private static String ID;
	
    @FXML
    private Label bookable;

    @FXML
    private TableColumn<Booking, String> bookedDate;
    
    @FXML
    private TableColumn<Booking, String> bookedTime;

    @FXML
    private TableColumn<Booking, Double> bookedDuration;

    @FXML
    private TableColumn<Booking, Integer> bookedEventID;

    @FXML
    private TableView<Booking> bookedEventsTable;

    @FXML
    private Label capacity;

    @FXML
    private Label category;

    @FXML
    private Button closeButton;

    @FXML
    private Label idAndTitle;

    @FXML
    private Label pricePerHour;

    @FXML
    private TableColumn<String, String> suitableEvent;

    @FXML
    private TableView<String> suitableEventTable;

    @FXML
    public void closeWindow(ActionEvent event) {
    	System.out.println("Window Closed");
    	Stage stage = (Stage) bookable.getScene().getWindow();
    	stage.close();
    }

    
    public void initialize() 
    {
    	TableListGenerator tlg = new TableListGenerator();
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	Venue selectedVenue = db.selectVenueNoType(ID);
    	
    	//Set bookable
    	if(selectedVenue.isBookable()) 
    	{
    		bookable.setText("True");    		
    	}
    	else 
    	{    		
    		bookable.setText("False");    		
    	}
//    	
//    	//Set Capacity
    	capacity.setText(""+selectedVenue.getCapacity());
//    	//Set Category
    	category.setText(selectedVenue.getCategory());
//    	//Set ID and Title
    	idAndTitle.setText(selectedVenue.getName());
//    	//Set Price Per Hour
    	pricePerHour.setText("$" + selectedVenue.getHirePrice());
    	//Set Suitable Events Table
    	suitableEvent.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
	    
	    suitableEventTable.setItems(tlg.getVenueTypes(ID));
	    
    	//Set Booked Table
	    bookedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	    bookedTime.setCellValueFactory(new PropertyValueFactory<>("time"));
	    bookedDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
	    bookedEventID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
	   
	    bookedEventsTable.setItems(tlg.getEventsVenue(ID));
    }


	public static void setName(String venueName) {
		System.out.println("Name: " + venueName);
		ID = venueName;
		System.out.println("ID: " + ID);
	}
    
}
