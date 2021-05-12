package ict.methodologies.Photos.controllers;

import ict.methodologies.Photos.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AlbumManagerController {

    @FXML
    private TextField albumNameTextField;

    @FXML
    private Button buttonOK;

    public void setImageId(int id) {
        this.imageId = id;
    }
    int imageId;

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();
        switch (buttonText) {
            case ("OK"):
                ImageManager.setIAlbum(imageId,albumNameTextField.getText());
                System.out.print(imageId);
                Stage stage = (Stage) buttonOK.getScene().getWindow();
                stage.close();

        }

    }
}
