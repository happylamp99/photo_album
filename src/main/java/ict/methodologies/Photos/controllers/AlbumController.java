package ict.methodologies.Photos.controllers;
import ict.methodologies.Photos.Editor.PhotoRotation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;

import java.nio.file.*;


public class AlbumController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField1;

    String imagePath;
    String location = "src/main/resources/images/";

    private Path to;
    private Path from;
    private File file;
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch(buttonText){
            case("Choose Image"):
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select Image File");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

                file = chooser.showOpenDialog(null);
                imagePath= file.toURI().toString();

                Image image1 = new Image(imagePath);
                imageView.setImage(image1);
// Rotation     PhotoRotation.rotate(file.getAbsolutePath(),angle);

                break;

            case("Insert"):
                if (file != null) {
                    from = Paths.get(file.toURI());
                    to = Paths.get(location + file.getName());
                    Files.copy(from, to);
                }
                break;

            case("Clear"):
                textField1.setText(" ");
                break;

        }
    }
}
