package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BookingManagerView {
	public void openBookingManager(Stage stage) 
	{
		try 
		{
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("requestsBooking.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Event Manager Login");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e) 
		{
			
		}
	}
}
