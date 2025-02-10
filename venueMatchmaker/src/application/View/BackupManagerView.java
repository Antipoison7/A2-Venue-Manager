package application.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BackupManagerView {
	public void openBackupManager(Stage stage) 
	{
		try 
		{
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("backupManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Backup Manager");
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
