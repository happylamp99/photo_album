package ict.methodologies.Photos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class AlbumController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField1;

    public void onMouseClick(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch(buttonText){
            case("Choose Image"):
                Image image = new Image("E:/Tzekos/IdeaProjects/photo_album/images/pc1.jpg");
                imageView.setImage(image);

                break;
            case("Clear"):
                textField1.setText(" ");
                break;

        }
    }
}
