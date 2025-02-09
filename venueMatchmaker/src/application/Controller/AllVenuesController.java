package application.Controller;

import java.util.ArrayList;

import application.Model.AllVenuesSearchModel;
import application.Model.TableListGenerator;
import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.User;
import application.Model.ObjectClasses.VenueDump;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BookingManagerView;
import application.View.EmployeeManagerView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AllVenuesController {

	private ArrayList<VenueDump> tableData = new ArrayList<VenueDump>();
	
	@FXML
    private MenuItem ddAllVenues;
	
	@FXML
    private MenuItem ddAllEvents;

    @FXML
    private MenuItem ddBackup;

    @FXML
    private MenuItem ddBookingManager;

    @FXML
    private MenuItem ddCustomise;

    @FXML
    private MenuItem ddEmployees;

    @FXML
    private MenuItem ddStats;

    @FXML
    private TextField searchBox;
    
    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<VenueDump, Integer> tCapacity;

    @FXML
    private TableColumn<VenueDump, String> tCategory;

    @FXML
    private TableColumn<VenueDump, Integer> tID;

    @FXML
    private TableColumn<VenueDump, String> tName;

    @FXML
    private TableColumn<VenueDump, Double> tPrice;

    @FXML
    private TableColumn<VenueDump, String> tSuitable;

    @FXML
    private TableView<VenueDump> table;
    
	
	public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) searchBox.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) searchBox.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) searchBox.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) searchBox.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) searchBox.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
    @FXML
    public void updateTableSearch(ActionEvent e) 
    {
//    	System.out.println("Update");
    	
    	if(searchBox.getText().length() == 0) 
    	{
    		tCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    		tCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

    		tID.setCellValueFactory(new PropertyValueFactory<>("id"));

    		tName.setCellValueFactory(new PropertyValueFactory<>("name"));

    		tPrice.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

    		tSuitable.setCellValueFactory(new PropertyValueFactory<>("type"));
        	
        	    
    		TableListGenerator mm = new TableListGenerator();
    		table.setItems(mm.getVenueDump());
    	}
    	else 
    	{
    		AllVenuesSearchModel mm = new AllVenuesSearchModel();
    		
    		tCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

    		tCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

    		tID.setCellValueFactory(new PropertyValueFactory<>("id"));

    		tName.setCellValueFactory(new PropertyValueFactory<>("name"));

    		tPrice.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

    		tSuitable.setCellValueFactory(new PropertyValueFactory<>("type"));
        	
    		
    		tableData = mm.getVenueDumpArrayList();
    		
    		table.setItems(mm.returnSearch(tableData, searchBox.getText()));
    	}
    }
    
    public void initialize()
    {
		tCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

		tCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		tID.setCellValueFactory(new PropertyValueFactory<>("id"));

		tName.setCellValueFactory(new PropertyValueFactory<>("name"));

		tPrice.setCellValueFactory(new PropertyValueFactory<>("hirePrice"));

		tSuitable.setCellValueFactory(new PropertyValueFactory<>("type"));
    	
    	    
		TableListGenerator mm = new TableListGenerator();
		table.setItems(mm.getVenueDump());
    }
}
