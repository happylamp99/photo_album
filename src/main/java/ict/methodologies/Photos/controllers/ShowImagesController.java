package ict.methodologies.Photos.controllers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import com.drew.metadata.*;
import com.drew.metadata.Directory;
import java.io.File;
import java.io.IOException;

public class ShowImagesController {
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

    public ShowImagesController() throws ImageProcessingException, IOException {
    }
}
