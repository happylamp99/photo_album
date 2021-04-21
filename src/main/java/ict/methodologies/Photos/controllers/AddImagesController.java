package ict.methodologies.Photos.controllers;
//import ict.methodologies.Photos.Editor.PhotoRotation;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.iptc.IptcReader;

import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import ict.methodologies.Photos.ImageManager;
import ict.methodologies.Photos.PhotosApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import ict.methodologies.Photos.ImageManager;
import java.io.*;

import java.nio.file.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;


public class AddImagesController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    String imagePath;
    private File file;
    public void onMouseClick(MouseEvent mouseEvent) throws IOException, ImageProcessingException {

        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();
        ImageManager imageManager = new ImageManager();
        switch(buttonText){
            case("Choose Image"):
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select Image File");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

                file = chooser.showOpenDialog(null);
                imagePath= file.toURI().toString();

                Image image1 = new Image(imagePath);
                imageView.setImage(image1);
                Random random = new Random();
                textFieldID.setText(String.valueOf(random.nextInt()));
                break;

            case("Insert"):
                Metadata metadata = ImageMetadataReader.readMetadata(file);
                Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
                boolean added = false;
                // obtain the Exif directory
                ExifSubIFDDirectory directory= metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

                // query the tag's value
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                for (GpsDirectory gpsDirectory : gpsDirectories) {
                // Try to read out the location, making sure it's non-zero
                    GeoLocation geoLocation = gpsDirectory.getGeoLocation();
                    if (geoLocation != null && !geoLocation.isZero()) {
                    imageManager.addImage(Integer.parseInt(textFieldID.getText()), textFieldName.getText(), textFieldCategory.getText(), imagePath, geoLocation.getLatitude(), geoLocation.getLongitude(),date);
                    added = true;
                    }
                }
                if (added !=true)
                imageManager.addImage(Integer.parseInt(textFieldID.getText()), textFieldName.getText(), textFieldCategory.getText(), imagePath,null,null,date);
                break;

            case("Clear"):
                textFieldID.setText(" ");
                textFieldName.setText(" ");
                textFieldCategory.setText(" ");
                break;
            case("Back"):
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Menu.fxml"));
                    Parent root = loader.load();

                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();
                }catch(IOException ex){
                    System.out.println(ex);

                }

        }
    }
}
