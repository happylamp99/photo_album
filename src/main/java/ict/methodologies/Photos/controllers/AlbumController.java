package ict.methodologies.Photos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class AlbumController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField1;

    public void onMouseClick(MouseEvent mouseEvent) throws FileNotFoundException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch(buttonText){
            case("Choose Image"):
                    InputStream stream = new FileInputStream("E:/Tzekos/IdeaProjects/photo_album/images/pc1.jpg");
                    Image image = new Image(stream);
                    imageView.setImage(image);

                break;

            case("Clear"):
                textField1.setText(" ");
                break;

        }
    }
}
