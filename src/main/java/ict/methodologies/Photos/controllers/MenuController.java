package ict.methodologies.Photos.controllers;

import ict.methodologies.Photos.PhotosApplication;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuController {
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case ("Edit Photos"):
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Album.fxml"));
                    Parent root = loader.load();

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

                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();
                }catch(IOException ex){
                    System.out.println(ex);
                }
                break;
            case("Exit"):
                System.exit(0);
                break;

        }
    }


}
