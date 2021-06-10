package ict.methodologies.Photos.controllers;

import ict.methodologies.Photos.PhotosApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button addPhotosBtn;

    @FXML
    private Button viewPhotosBtn;

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case ("Add Photos"):
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/AddImages.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) addPhotosBtn.getScene().getWindow();
                    stage.hide();
                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();

                }catch(IOException ex){
                    System.out.println(ex);
                }
                break;
            case ("View Photos"):
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/ShowImages.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) addPhotosBtn.getScene().getWindow();
                    stage.hide();
                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();
                }catch(IOException ex){
                    System.out.println(ex);
                }
                break;
            case ("View Albums"):
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/AlbumViewer.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) addPhotosBtn.getScene().getWindow();
                stage.hide();
                PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                PhotosApplication.getShowImagesStage().show();
            case("Exit"):
                System.exit(0);
                break;

        }
    }


}
