package application.Controller;

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

public class SummaryController {

    @FXML
    private BarChart<?, ?> barchart;

    @FXML
    private CategoryAxis barchartCategory;

    @FXML
    private NumberAxis barchartNumber;

    @FXML
    private TableColumn<?, ?> clientClient;

    @FXML
    private TableColumn<?, ?> clientComm;

    @FXML
    private TableColumn<?, ?> clientCost;

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
    private TableColumn<?, ?> jobCommission;

    @FXML
    private TableColumn<?, ?> jobCost;

    @FXML
    private TableColumn<?, ?> jobId;

    @FXML
    private TableColumn<?, ?> jobStaff;

    @FXML
    private TableView<?> perClientTable;

    @FXML
    private TableView<?> perJobTable;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label totalCommissions;

    @FXML
    void openAddEmployees(ActionEvent event) {

    }

    @FXML
    void openAllEvents(ActionEvent event) {

    }

    @FXML
    void openAllVenues(ActionEvent event) {

    }

    @FXML
    void openBackupManager(ActionEvent event) {

    }

    @FXML
    void openBookingManager(ActionEvent event) {

    }

    @FXML
    void openCustomiseProfile(ActionEvent event) {

    }

    @FXML
    void openManagerStats(ActionEvent event) {

    }

}
