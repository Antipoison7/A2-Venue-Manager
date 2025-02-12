package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookVenueView {
	public void openBookVenue(Stage stage) 
	{
		try 
		{
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("bookVenue.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Book Venue");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
}
