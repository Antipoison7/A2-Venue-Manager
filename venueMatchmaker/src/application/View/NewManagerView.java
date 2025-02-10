package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewManagerView {
	public void openNewUserView(Stage stage) 
	{
		try 
		{
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("newManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Employee Manager");
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
