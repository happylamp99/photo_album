package ict.methodologies.Photos.controllers;

import ict.methodologies.Photos.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MetadataViewerController {

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    @FXML
    private TextField textFieldLong;

    @FXML
    private TextField textFieldLat;

    @FXML
    private TextField textFieldDate;

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
            case ("View"):
               ImageManager.getImage(imageId);
               textFieldName.setText(ImageManager.getImageName());
               textFieldCategory.setText(ImageManager.getImageCategory());
               textFieldLong.setText(String.valueOf(ImageManager.getImageLong()));
               textFieldLat.setText(String.valueOf(ImageManager.getImageLat()));
               textFieldDate.setText(String.valueOf(ImageManager.getImageDate()));
                break;
            case ("OK"):
                Stage stage = (Stage) buttonOK.getScene().getWindow();
                stage.close();
                break;
        }
    }
}