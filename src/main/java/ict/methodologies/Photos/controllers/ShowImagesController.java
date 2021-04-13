package ict.methodologies.Photos.controllers;


import ict.methodologies.Photos.ImageManager;
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


public class ShowImagesController {
    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    @FXML
    private ImageView imageView2;
    public void ShowImagesController(){

    }
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();


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
                ImageManager.getImages();

                int id = Integer.parseInt(textFieldID.getText());
                ImageManager.getImage(id);
                String url = "file:"+ImageManager.getImageURL();
                Image image1 = new Image(url);

                textFieldName.setText(ImageManager.getImageName());
                textFieldCategory.setText(ImageManager.getImageCategory());
                imageView2.setImage(image1);
                break;
            case ("Delete"):
                id = Integer.parseInt(textFieldID.getText());
                ImageManager.deleteImage(id);
                textFieldID.setText(" ");
                textFieldName.setText(" ");
                textFieldCategory.setText(" ");
                imageView2.setImage(null);
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