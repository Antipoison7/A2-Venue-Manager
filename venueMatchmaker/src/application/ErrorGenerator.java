package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;


public class ErrorGenerator{
	private String errorTitle = "Warning: Unnamed Error";
	private String errorBody = "Something went wrong, I didn't expect this to happen. An error has occured within the error. Please message Connor about this because he probably forgot something.";
	
	public void setErrorTitle(String t) 
	{
		errorTitle = "Warning: " + t;
	}
	
	public void setErrorBody(String b) 
	{
		errorBody = b;
	}
	
	public void throwError() {
		try {
			Stage errorStage = new Stage();
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("errorPage.fxml"));
			
			GridPane titleBox = new GridPane();
			
			GridPane.setMargin(titleBox, new Insets(0, 0, 5, 0));
			
			//Set Title Error Text
			FileInputStream imageStream = new FileInputStream("src/application/resources/Warning.png");
			Image warning = new Image(imageStream);
			ImageView warningImage = new ImageView(warning);
			warningImage.setFitWidth(33);
			warningImage.setFitHeight(33);
			
			Label titleText = new Label(errorTitle);
			Font font = Font.font("System", FontWeight.BOLD, 20);
			titleText.setFont(font);
			
			titleBox.add(warningImage, 0, 0);
			titleBox.add(titleText, 1, 0);
			
			root.setTop(titleBox);
			
			
			//Set Body Error Text
			Label bodyText = new Label(errorBody);
			Font fontB = Font.font(16);
			bodyText.setFont(fontB);
			bodyText.setWrapText(true);
			
			root.setCenter(bodyText);	
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			errorStage.setTitle("Event Manager Login");
			errorStage.setScene(scene);
			
			errorStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
