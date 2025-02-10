package application.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewBookingView 
{	
	public void openBookingWindow(Stage bookingWindow) 
	{
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("newBookingRequest.fxml"));

			Scene scene = new Scene(root);
			bookingWindow.setTitle("New Venue");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			bookingWindow.setResizable(false);
			bookingWindow.setScene(scene);
			
			bookingWindow.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
