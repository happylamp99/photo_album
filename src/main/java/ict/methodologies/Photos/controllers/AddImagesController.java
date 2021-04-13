package ict.methodologies.Photos.controllers;
//import ict.methodologies.Photos.Editor.PhotoRotation;
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
    String location = "src/main/resources/images/";
    private Path to;
    private Path from;
    private File file;
    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
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
                textFieldID.setText(String.valueOf(random.nextInt(1000)));
// Rotation     PhotoRotation.rotate(file.getAbsolutePath(),angle);

                break;

            case("Insert"):
                if (file != null) {
                    from = Paths.get(file.toURI());
                    to = Paths.get(location + file.getName());
                    Files.copy(from, to);
                }
                imageManager.addImage(Integer.parseInt(textFieldID.getText()),textFieldName.getText(),textFieldCategory.getText(),location + file.getName() );

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
