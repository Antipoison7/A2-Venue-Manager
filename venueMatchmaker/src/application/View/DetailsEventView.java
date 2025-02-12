package application.View;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetailsEventView{
	public void openNewEventDetails() 
	{
		try 
		{
			Stage stage = new Stage();
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("moreEventsDetails.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("More Event Details");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch(LoadException e) 
		{
			System.out.println(e);
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
}
