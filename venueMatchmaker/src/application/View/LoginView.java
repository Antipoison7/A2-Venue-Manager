package application.View;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class LoginView extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("requestsBooking.fxml"));
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Event Manager Login");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
