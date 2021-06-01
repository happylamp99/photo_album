package ict.methodologies.Photos.controllers;
//import ict.methodologies.Photos.Editor.PhotoRotation;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

import com.drew.lang.GeoLocation;
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
import javafx.stage.Stage;

import java.io.*;

import java.util.*;


public class AddImagesController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    @FXML
    private Button insertBtn;

    @FXML
    private Button clearBtn;

    String imagePath;
    private File file;
    String albumName;

    public static Date MetaDataReaderDate(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        // obtain the Exif directory
        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        // query the tag's value
        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        return date;
    }

    public static double[] MetaDataReaderGeoLocation(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
        double[] answer = new double[0];
        for (GpsDirectory gpsDirectory : gpsDirectories) {
            // Try to read out the location, making sure it's non-zero
            GeoLocation geoLocation = gpsDirectory.getGeoLocation();

            if (geoLocation != null && !geoLocation.isZero()) {
                answer = new double[3];
                answer[0] = geoLocation.getLatitude();
                answer[1] = geoLocation.getLongitude();
                answer[2] = 1;
            }
            else {
                answer = new double[3];
                answer[0] = 0;
                answer[1] = 0;
                answer[2] = 0;
            }
        }
        return answer;
    }

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
                //Random random = new Random();
                //textFieldID.setText(String.valueOf(random.nextInt()));
                insertBtn.setVisible(true);
                clearBtn.setVisible(true);
                break;

            case("Choose Images"):
                FileChooser chooser1 = new FileChooser();
                chooser1.setTitle("Select Multiple Image Files");
                chooser1.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
                List<File> files = chooser1.showOpenMultipleDialog(null);
                try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/AlbumManager.fxml"));
                Parent root = loader.load();
                AlbumManagerController albumManagerController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.showAndWait();

                albumName = albumManagerController.getAlbumName();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                for (File file : files) {

                    imagePath= file.toURI().toString();
                    Date date = MetaDataReaderDate(file);
                    double[] LanLon = MetaDataReaderGeoLocation(file);
                    if(LanLon[2] == 1)
                        imageManager.addImage(textFieldName.getText(), textFieldCategory.getText(), imagePath, LanLon[0], LanLon[1],date,albumName);
                    else
                        imageManager.addImage(textFieldName.getText(), textFieldCategory.getText(), imagePath,null,null,date,albumName);
                }

            case("Insert"):
                Date date = MetaDataReaderDate(file);
                double[] LanLon = MetaDataReaderGeoLocation(file);
                if(LanLon[2] == 1)
                    imageManager.addImage(textFieldName.getText(), textFieldCategory.getText(), imagePath, LanLon[0], LanLon[1], date);
                else
                    imageManager.addImage(textFieldName.getText(), textFieldCategory.getText(), imagePath,null,null,date);
                break;

            case("Clear"):
                textFieldID.setText(" ");
                textFieldName.setText(" ");
                textFieldCategory.setText(" ");
                break;

            case("Back"):
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
                    Parent root = loader.load();

                    PhotosApplication.getShowImagesStage().setScene(new Scene(root));
                    PhotosApplication.getShowImagesStage().show();
                }catch(IOException ex){
                    System.out.println(ex);

                }

        }
    }
}
