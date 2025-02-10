package application.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewVenueView 
{	
	public void openVenueWindow(Stage venueWindow) 
	{
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("newVenue.fxml"));

			Scene scene = new Scene(root);
			venueWindow.setTitle("New Venue");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			venueWindow.setResizable(false);
			venueWindow.setScene(scene);
			
			venueWindow.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
