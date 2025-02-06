package application.View;

import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NewVenueView 
{	
	public void openNewVenueWindow() 
	{
		try {
			Stage venueWindow = new Stage();
			
			
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
