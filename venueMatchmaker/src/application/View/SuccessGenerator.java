package application.View;
	
import java.io.FileInputStream;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;


public class SuccessGenerator{
	private String successTitle = "Warning: Unnamed Error";
	private String successBody = "Something went wrong, I didn't expect this to happen. An error has occured within the error. Please message Connor about this because he probably forgot something.";
	
	public void setSuccessTitle(String t) 
	{
		successTitle = "Success: " + t;
	}
	
	public void setSuccessBody(String b) 
	{
		successBody = b;
	}
	
	public void throwSuccess() {
		try {
			Stage errorStage = new Stage();
			
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("successPage.fxml"));
			
			GridPane titleBox = new GridPane();
			
			GridPane.setMargin(titleBox, new Insets(0, 0, 5, 0));
			
			//Set Title Error Text
			FileInputStream imageStream = new FileInputStream("src/application/resources/Success.png");
			Image warning = new Image(imageStream);
			ImageView warningImage = new ImageView(warning);
			warningImage.setFitWidth(33);
			warningImage.setFitHeight(33);
			
			Label titleText = new Label(successTitle);
			Font font = Font.font("System", FontWeight.BOLD, 20);
			titleText.setFont(font);
			
			titleBox.add(warningImage, 0, 0);
			titleBox.add(titleText, 1, 0);
			
			root.setTop(titleBox);
			
			
			//Set Body Error Text
			Label bodyText = new Label(successBody);
			Font fontB = Font.font(16);
			bodyText.setFont(fontB);
			bodyText.setWrapText(true);
			
			root.setCenter(bodyText);	
			
			Scene scene = new Scene(root);
			errorStage.setTitle("Success");
			errorStage.setResizable(true);
			errorStage.setScene(scene);
			
			errorStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
