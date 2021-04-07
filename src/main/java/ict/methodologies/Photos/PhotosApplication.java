package ict.methodologies.Photos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PhotosApplication extends Application{
	private static Stage ShowImagesStage=null;
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
		stage.setTitle("Menu");
		stage.setScene(new Scene(root));
		stage.show();
		createShowImagesStage();
	}
	public static void main(String[] args) {
		SpringApplication.run(PhotosApplication.class, args);
		launch();
	}
	public static Stage getShowImagesStage(){
		return ShowImagesStage;
	}
	public void createShowImagesStage(){
		ShowImagesStage=new Stage();
		ShowImagesStage.setTitle("Images");
		ShowImagesStage.setAlwaysOnTop(true);
		ShowImagesStage.setResizable(false);
		ShowImagesStage.initModality(Modality.APPLICATION_MODAL);
	}
}
