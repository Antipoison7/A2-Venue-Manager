module venueMatchmaker {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.Controller to javafx.graphics, javafx.fxml;
	opens application.Model to javafx.graphics, javafx.fxml;
	opens application.View to javafx.graphics, javafx.fxml;
}
