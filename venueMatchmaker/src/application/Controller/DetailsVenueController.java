package application.Controller;

import application.Model.ObjectDBInterface;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.Venue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DetailsVenueController {
	
	private static int ID;

    @FXML
    private Label bookable;

    @FXML
    private TableColumn<?, ?> bookedDateTime;

    @FXML
    private TableColumn<?, ?> bookedDuration;

    @FXML
    private TableColumn<?, ?> bookedEventID;

    @FXML
    private TableView<?> bookedEventsTable;

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
    private TableColumn<?, ?> suitableEvent;

    @FXML
    private TableView<?> suitableEventTable;

    @FXML
    public void closeWindow(ActionEvent event) {

    }

    
    public void initialize() 
    {
    	System.out.println("ping");
    	
    	TableListGenerator tlg = new TableListGenerator();
    	ObjectDBInterface db = new ObjectDBInterface();
    	
    	Venue selectedVenue = db.selectVenueNoType(25);
    	
    	
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
    	idAndTitle.setText("#" + selectedVenue.getId() + " | " + selectedVenue.getName());
//    	//Set Price Per Hour
    	pricePerHour.setText("$" + selectedVenue.getHirePrice());
    	//Set Suitable Events Table
    	
    	//Set Booked Table
    }


	public static void setID(int id) {
		ID = id;
	}
    
}
