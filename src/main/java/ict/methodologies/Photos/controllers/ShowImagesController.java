package ict.methodologies.Photos.controllers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import ict.methodologies.Photos.PhotosApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.drew.metadata.*;
import com.drew.metadata.Directory;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;


public class ShowImagesController {
    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();


        switch (buttonText) {
            case("Clear"):
                textField1.setText(" ");
                textField2.setText(" ");
                textField3.setText(" ");
                break;


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
        }
    }
}

//import com.drew.imaging.ImageMetadataReader;
//import com.drew.imaging.ImageProcessingException;
//import com.drew.metadata.exif.GpsDescriptor;
//import com.drew.metadata.exif.GpsDirectory;
//import com.drew.metadata.Directory;
//    @FXML
//    private ImageView imageView2;
//    File jpegFile = new File("src/main/resources/images/pic.png");
//    Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
//    for (Directory directory : metadata.getDirectories()) {
//        for (Tag tag : directory.getTags()) {
//            System.out.format("[%s] - %s = %s",
//                    directory.getName(), tag.getTagName(), tag.getDescription());
//        }
//        if (directory.hasErrors()) {
//            for (String error : directory.getErrors()) {
//                System.err.format("ERROR: %s", error);
//            }
//        }
//    }