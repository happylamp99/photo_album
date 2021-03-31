package ict.methodologies.Photos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PhotosApplication extends Application{
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Album.fxml"));
		stage.setTitle("Album");
		stage.setScene(new Scene(root));
		stage.show();
	}
	public static void main(String[] args) {
		SpringApplication.run(PhotosApplication.class, args);
		launch();
	}

}
