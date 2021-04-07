package ict.methodologies.Photos.controllers;
import ict.methodologies.Photos.Editor.PhotoRotation;
import ict.methodologies.Photos.ImageManager;
import ict.methodologies.Photos.PhotosApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import ict.methodologies.Photos.ImageManager;
import java.io.*;

import java.nio.file.*;
import java.util.Random;


public class AlbumController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    String imagePath;
    private File file;
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();
        ImageManager imageManager = null;
        switch(buttonText){
            case("Choose Photos"):
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select Photos File");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Photos Files", "*.png", "*.jpg", "*.jpeg"));

                file = chooser.showOpenDialog(null);
                imagePath= file.toURI().toString();

                Image image1 = new Image(imagePath);
                imageView.setImage(image1);
                Random random = new Random();
                textFieldID.setText(random.toString());
// Rotation     PhotoRotation.rotate(file.getAbsolutePath(),angle);

                break;

            case("Insert"):
            {
                    imageManager.addImage(Integer.parseInt(textFieldID.getText()),textFieldName.getText(),textFieldCategory.getText(),imagePath );
                    imageManager.getImages();
                }
                break;

            case("Clear"):
                textFieldID.setText(" ");
                textFieldName.setText(" ");
                textFieldCategory.setText(" ");
                break;
            case("Back"):
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Menu.fxml"));
                    Parent root = loader.load();

                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();
                }catch(IOException ex){
                    System.out.println(ex);

                }

        }
    }

}
