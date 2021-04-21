package ict.methodologies.Photos.controllers;

//import ict.methodologies.Photos.Editor.PhotoRotation;
import ict.methodologies.Photos.ImageManager;
import ict.methodologies.Photos.Models.Photos;
import ict.methodologies.Photos.PhotosApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

import java.io.IOException;

import java.util.List;


public class ShowImagesController {
    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    @FXML
    private ImageView imageView2;

    @FXML
    private TextField textFieldLong;

    @FXML
    private TextField textFieldLat;

    @FXML
    private TextField textFieldDate;

    public void ShowImagesController(){

    }
    int imgIndex=0;
    int angle=0;
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();
        List<Photos> photos= ImageManager.getImages();
        switch (buttonText) {
            case ("Back"):
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
                    Parent root = loader.load();

                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();
                } catch (IOException ex) {
                    System.out.println(ex);
                    break;


                }
            case ("Refresh"):
                photos=ImageManager.getImages();
                imgIndex=0;
                Image image1 = new Image(photos.get(0).getiURL());
                textFieldID.setText(String.valueOf(photos.get(0).getId()));
                textFieldName.setText(photos.get(0).getiName());
                textFieldCategory.setText(photos.get(0).getiCategory());
                textFieldLong.setText(String.valueOf(photos.get(0).getiLong()));
                textFieldLat.setText(String.valueOf(photos.get(0).getiLat()));
                textFieldDate.setText(String.valueOf(photos.get(0).getDate()));
                imageView2.setImage(image1);
                break;
            case ("Delete"):
                ImageManager.deleteImage(photos.get(imgIndex).getId());
                textFieldID.setText(" ");
                textFieldName.setText(" ");
                textFieldCategory.setText(" ");
                imageView2.setImage(null);
                break;
            case(">>"):
                imgIndex+=1;
                image1= new Image(photos.get(imgIndex).getiURL());
                textFieldID.setText(String.valueOf(photos.get(imgIndex).getId()));
                textFieldName.setText(photos.get(imgIndex).getiName());
                textFieldCategory.setText(photos.get(imgIndex).getiCategory());
                textFieldLong.setText(String.valueOf(photos.get(imgIndex).getiLong()));
                textFieldLat.setText(String.valueOf(photos.get(imgIndex).getiLat()));
                textFieldDate.setText(String.valueOf(photos.get(imgIndex).getDate()));
                imageView2.setImage(image1);
                 break;
            case("<<"):
                imgIndex -= 1;
                image1 = new Image(photos.get(imgIndex).getiURL());
                textFieldID.setText(String.valueOf(photos.get(imgIndex).getId()));
                textFieldName.setText(photos.get(imgIndex).getiName());
                textFieldCategory.setText(photos.get(imgIndex).getiCategory());
                textFieldLong.setText(String.valueOf(photos.get(imgIndex).getiLong()));
                textFieldLat.setText(String.valueOf(photos.get(imgIndex).getiLat()));
                textFieldDate.setText(String.valueOf(photos.get(imgIndex).getDate()));
                imageView2.setImage(image1);
                break;
//            case("Rotate 90"):
//                angle+=90;
//                PhotoRotation.rotate(String.valueOf(photos.get(imgIndex).getiURL()),angle);
//                image1 = new Image(photos.get(imgIndex).getiURL());
//                imageView2.setImage(image1);
            }
    }
}