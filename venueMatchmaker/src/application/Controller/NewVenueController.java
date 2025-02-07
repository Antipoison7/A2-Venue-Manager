package application.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import application.Model.*;
import application.Model.ObjectClasses.Venue;
import application.View.BookingManagerView;
import application.View.ErrorGenerator;

public class NewVenueController {

	//The check Boxes in the Event Types section
    @FXML
    private CheckBox checkDisco;

    @FXML
    private CheckBox checkFestival;

    @FXML
    private CheckBox checkGig;

    @FXML
    private CheckBox checkLLC;
    
    @FXML
    private CheckBox checkLive;

    //Submit Button
    @FXML
    private Button createVenue;
    
    //Discard Button
    @FXML
    private Button buttonDiscard;

    //vbox that contains the check boxes, could be useful if the product wants to create events dynamically
    @FXML
    private VBox typeVBox;

    //Capacity
    @FXML
    private TextField venueCap;

    //Indoor / Outdoor
    @FXML
    private ComboBox<String> venueCondition;

    //Name
    @FXML
    private TextField venueName;

    //Price Per Hour
    @FXML
    private TextField venuePrice;
    
    
    
    @FXML
    public void createVenue() 
    {
    	try 
    	{
    		NewVenueModel venueModel = new NewVenueModel();
        	
        	Venue userVenue = new Venue();
        	
        	ArrayList<String> suitableTypes = new ArrayList<String>();
        	
        	if(checkDisco.isSelected()) 
        	{
        		suitableTypes.add("disco");
        	}
        	
        	if(checkFestival.isSelected()) 
        	{
        		suitableTypes.add("festival");
        	}
        	
        	if(checkGig.isSelected()) 
        	{
        		suitableTypes.add("gig");
        	}
        	
        	if(checkLLC.isSelected()) 
        	{
        		System.out.println("Large Live");
        		suitableTypes.add("large live concert");
        	}
        	
        	if(checkLive.isSelected()) 
        	{
        		System.out.println("Live");
        		suitableTypes.add("live concert");
        	}
        	
        	userVenue.setCapacity(Integer.parseInt(venueCap.getText()));
        	userVenue.setCategory(venueCondition.getValue().toString());
        	userVenue.setHirePrice(Double.parseDouble(venuePrice.getText()));
        	userVenue.setName(venueName.getText());
        	userVenue.setSuitableType(suitableTypes);
        	userVenue.setId(0);
        	
        	if(venueModel.isEntryValid(userVenue)) 
        	{
        		if(!venueModel.addNewVenue(userVenue)) 
        		{
        			ErrorGenerator errorThrow = new ErrorGenerator();
        	    	
        	    	errorThrow.setErrorTitle("DB Error");
        	    	errorThrow.setErrorBody("Something went wrong, not too sure what, ask your local developer or IT guy.");
        	    	
        	    	errorThrow.throwError();
        		}
        		else 
        		{
        			//TODO: Make this update the bookingManagerView
        			System.out.println("Window Closed");
        	    	Stage stage = (Stage) createVenue.getScene().getWindow();
        	    	
        	    	BookingManagerView view = new BookingManagerView();
        	    	view.openBookingManager(stage);
        		}
        	}
        	else 
        	{
        		ErrorGenerator errorThrow = new ErrorGenerator();
    	    	
    	    	errorThrow.setErrorTitle("Invalid values");
    	    	errorThrow.setErrorBody("One of the fields is invalid, please fix it and try again");
    	    	
    	    	errorThrow.throwError();
        	}
    	}
    	catch(Exception e) 
    	{
    		ErrorGenerator errorThrow = new ErrorGenerator();
	    	
	    	errorThrow.setErrorTitle("Invalid values");
	    	errorThrow.setErrorBody("One of the fields is invalid, please fix it and try again (e)");
	    	
	    	errorThrow.throwError();
    	}
    }
    
    @FXML
    public void discard() 
    {
    	Stage stage = (Stage) buttonDiscard.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void initialize() 
    {
    	ObservableList<String> data = FXCollections.observableArrayList("Indoor","Convertible","Outdoor");
    	
    	venueCondition.setItems(data);
    }

}
