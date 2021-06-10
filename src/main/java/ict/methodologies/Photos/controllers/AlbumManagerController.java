package ict.methodologies.Photos.controllers;

import com.fasterxml.classmate.Annotations;
import com.zaxxer.hikari.util.FastList;
import ict.methodologies.Photos.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AlbumManagerController {

    @FXML
    private TextField albumNameTextField;

    @FXML
    private Button buttonOK;

    @FXML
    private TextField categorybox;

    @FXML
    private TextField peoplebox;

    @FXML
    private TextField radiusbox;

    @FXML
    private CheckBox catck;

    @FXML
    private CheckBox peopleck;

    @FXML
    private CheckBox radck;

    public String getAlbumName() {
        return AlbumName;
    }
    String AlbumName;
    int cat=0;
    int pep=0;
    int rad=0;
    List<Integer> ids = null;

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();
        switch (buttonText) {
            case ("OK"):
                if (catck.isSelected()){

                    ids.addAll(ids.size(),ImageManager.searchImages(categorybox.getText()));
                    cat=1;
                }
                if (peopleck.isSelected()) {
                    ids.addAll(ids.size(),ImageManager.searchImages(peoplebox.getText()));
                    pep=1;
                }
                if (radck.isSelected()){
                    ids.addAll(ids.size(),ImageManager.searchImages(radiusbox.getText()));
                    rad=1;
                }
                for (int i=0;i<ids.size();i++){


                }
                AlbumName=albumNameTextField.getText();
                Stage stage = (Stage) buttonOK.getScene().getWindow();
                stage.close();
        }

    }
}
