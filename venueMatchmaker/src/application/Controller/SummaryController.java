package application.Controller;

import application.Model.ObjectClasses.CurrentUser;
import application.Model.ObjectClasses.SummaryPerClient;
import application.Model.ObjectClasses.SummaryPerJob;
import application.View.AllEventsView;
import application.View.AllVenuesView;
import application.View.BackupManagerView;
import application.View.BookingManagerView;
import application.View.DataSummaryView;
import application.View.EmployeeManagerView;
import application.View.LoginView;
import application.View.UpdateStaffProfileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SummaryController {

    @FXML
    private BarChart<?, ?> barchart;

    @FXML
    private CategoryAxis barchartCategory;

    @FXML
    private NumberAxis barchartNumber;

    //Commission per client table
    @FXML
    private TableColumn<SummaryPerClient, String> clientClient;
    
    @FXML
    private TableColumn<SummaryPerClient, String> clientCost;

    @FXML
    private TableColumn<SummaryPerClient, String> clientComm;

    @FXML
    private TableView<SummaryPerClient> perClientTable;

    @FXML
    private MenuItem ddAllEvents;

    @FXML
    private MenuItem ddAllVenues;

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
    private MenuItem ddLogout;

    //Commission per job table
    @FXML
    private TableColumn<SummaryPerJob, ?> jobId;
    
    @FXML
    private TableColumn<SummaryPerJob, ?> jobStaff;
    
    @FXML
    private TableColumn<SummaryPerJob, ?> jobCost;
    
    @FXML
    private TableColumn<SummaryPerJob, ?> jobCommission;


    @FXML
    private TableView<?> perJobTable;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label totalCommissions;

    @FXML
    public void openBookingManager(ActionEvent e) 
    {
    	System.out.println("Open Booking Manager");
    	
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	
    	BookingManagerView view = new BookingManagerView();
    	view.openBookingManager(stage);
    }
    
    @FXML
    public void openCustomiseProfile(ActionEvent e) 
    {
    	System.out.println("Open Customise Profile");
    	
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	
    	UpdateStaffProfileView view = new UpdateStaffProfileView();
    	view.openProfileCustomisation(stage);
    }
    
    @FXML
    public void openAllVenues(ActionEvent event) {
    	System.out.println("Open All Venues Menu");
    	
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	
    	AllVenuesView view = new AllVenuesView();
    	view.openAllVenues(stage);
    }
    
    @FXML
    public void openAllEvents(ActionEvent event) 
    {
    	System.out.println("Open All Events Menu");
    	
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	
    	AllEventsView view = new AllEventsView();
    	view.openAllEvents(stage);
    }
    
    @FXML
    public void openBackupManager(ActionEvent e) 
    {
    	System.out.println("Open Backup Manager");
    	
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	
    	BackupManagerView view = new BackupManagerView();
    	view.openBackupManager(stage);
    }
    
    @FXML
    public void openManagerStats(ActionEvent e) 
    {
    	System.out.println("Open Manager Stats");
    	
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	
    	DataSummaryView view = new DataSummaryView();
    	view.openSummary(stage);
    }
    
    @FXML
    public void openAddEmployees(ActionEvent e) 
    {
    	System.out.println("Open Add Employees");
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	EmployeeManagerView empl = new EmployeeManagerView();
    	empl.openManagerView(stage);
    }
    
    @FXML
    public void logOut(ActionEvent e) 
    {
    	System.out.println("Log Out");
    	Stage stage = (Stage) totalCommissions.getScene().getWindow();
    	LoginView logOut = new LoginView();
    	logOut.start(stage);
    }
    
    public void initialize() 
    {
    	if(CurrentUser.getUser().getSecurity() <=0) 
    	{
    		    ddEmployees.setVisible(false);

    		    ddStats.setVisible(false);
    	}
    }

}
