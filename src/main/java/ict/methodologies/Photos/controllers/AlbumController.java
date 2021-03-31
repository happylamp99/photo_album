package ict.methodologies.Photos.controllers;
import ict.methodologies.Photos.Editor.PhotoRotation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class AlbumController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField1;

    String location = "E:/Tzekos/IdeaProjects/photo_album/savedImages";
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch(buttonText){
            case("Choose Image"):
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select Image File");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
                File file = chooser.showOpenDialog(null);
                Image image1 = new Image(file.toURI().toString());
                imageView.setImage(image1);
                break;

            case("Insert"):



            case("Clear"):
                textField1.setText(" ");
                break;

        }
    }
}
