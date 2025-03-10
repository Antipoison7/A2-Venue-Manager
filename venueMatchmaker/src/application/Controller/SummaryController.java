package application.Controller;

import java.util.HashMap;

import application.Model.SummaryModel;
import application.Model.ObjectClasses.BarchartResult;
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
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SummaryController {

    @FXML
    private BarChart<String, Double> barchart;

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
    private TableColumn<SummaryPerJob, Integer> jobId;
    
    @FXML
    private TableColumn<SummaryPerJob, String> jobStaff;
    
    @FXML
    private TableColumn<SummaryPerJob, String> jobCost;
    
    @FXML
    private TableColumn<SummaryPerJob, String> jobCommission;


    @FXML
    private TableView<SummaryPerJob> perJobTable;

    @FXML
    private HBox pieChartBox;

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
    	
    	SummaryModel model = new SummaryModel();
    	
    	//Set Commissions per job
    	jobId.setCellValueFactory(new PropertyValueFactory<>("jobID"));

 	    jobStaff.setCellValueFactory(new PropertyValueFactory<>("staff"));

 	    jobCost.setCellValueFactory(new PropertyValueFactory<>("costString"));

 	    jobCommission.setCellValueFactory(new PropertyValueFactory<>("costString"));
 	    
 	    perJobTable.setItems(model.getCommPerJob());
 	    
 	    
 	    //Set Commissions per client
    	clientClient.setCellValueFactory(new PropertyValueFactory<>("client"));

 	    clientCost.setCellValueFactory(new PropertyValueFactory<>("costString"));

 	    clientComm.setCellValueFactory(new PropertyValueFactory<>("commissionString"));

 	    perClientTable.setItems(model.getCommPerClient());
 	    
 	    //Set Total Commissions to date
 	    totalCommissions.setText(model.getTotalCommissions());
 	    
 	    //Set The Barchart Values
 	    
 	    XYChart.Series<String,Double> seriesIncome = new XYChart.Series<String,Double>();
 	    seriesIncome.setName("Income $");
 	    
 	    XYChart.Series<String,Double> seriesCommission = new XYChart.Series<String,Double>();
	    seriesCommission.setName("Commission $");
	    
	    for(BarchartResult b : model.getBarchart()) 
	    {
	    	seriesIncome.getData().add(new XYChart.Data<String, Double>(b.getTitle(), b.getCost()));
	    	seriesCommission.getData().add(new XYChart.Data<String, Double>(b.getTitle(), b.getCommission()));
	    }
	    
	    barchart.getData().add(seriesIncome);
	    barchart.getData().add(seriesCommission);
	    
	    //Set Pie Chart Values
	    PieChart pieChart = new PieChart();
	    HashMap<String, Double> slices = model.getPieChart();
//	    System.out.println(slices);
	    
	    for(String s :	slices.keySet()) 
	    {
	    	PieChart.Data slice = new PieChart.Data(s, slices.get(s));
	    	pieChart.getData().add(slice);
	    }
	    
	    pieChart.setPrefWidth(600);
	    pieChart.setMaxWidth(600);
	    pieChart.setPrefHeight(400);
	    pieChart.setMaxHeight(400);
	    
	    pieChartBox.getChildren().add(pieChart);
	    
    }

}
