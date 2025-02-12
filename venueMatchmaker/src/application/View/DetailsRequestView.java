package application.View;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetailsRequestView{
	public void openNewRequestDetails() 
	{
		try 
		{
			Stage stage = new Stage();
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("moreRequestDetails.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("More Request Details");
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
