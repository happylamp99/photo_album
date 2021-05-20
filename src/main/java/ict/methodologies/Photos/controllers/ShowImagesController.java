package ict.methodologies.Photos.controllers;

//import ict.methodologies.Photos.Editor.PhotoRotation;
import ict.methodologies.Photos.ImageManager;
import ict.methodologies.Photos.Models.Photos;
import ict.methodologies.Photos.PhotosApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.List;


public class ShowImagesController {
    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCategory;

    @FXML
    private ImageView imageView2;

    @FXML
    private TextField textFieldLong;

    @FXML
    private TextField textFieldLat;

    @FXML
    private TextField textFieldDate;


    String albumName;

    public void ShowImagesController(){
        ContextMenu contextMenu = new ContextMenu();
        Menu item1 = new Menu("Add to Album");
        MenuItem sub1 = new MenuItem("New Album");
        MenuItem sub2 = new MenuItem("Existing Album");
        item1.getItems().addAll(sub1,sub2);
        MenuItem item2 = new MenuItem("Show Metadata");
        MenuItem item3 = new MenuItem("Delete Image");


        sub1.setOnAction((ActionEvent e) ->{

            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/AlbumManager.fxml"));
                Parent root = loader.load();
                AlbumManagerController albumManagerController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.showAndWait();

                albumName = albumManagerController.getAlbumName();
                ImageManager.setIAlbum(Integer.parseInt(textFieldID.getText()),albumName);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        item2.setOnAction((ActionEvent e) ->{
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/MetadataViewer.fxml"));
                Parent root = loader.load();
                MetadataViewerController metadataViewerController = loader.getController();
                metadataViewerController.setImageId(Integer.parseInt(textFieldID.getText()));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setAlwaysOnTop(true);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        item3.setOnAction((ActionEvent e) ->{
            List<Photos> photos= ImageManager.getImages();
            ImageManager.deleteImage(photos.get(imgIndex).getId());
            textFieldID.setText(" ");
            textFieldName.setText(" ");
            textFieldCategory.setText(" ");
            imageView2.setImage(null);
        });
        contextMenu.getItems().addAll(item1,item2,item3);
        imageView2.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                contextMenu.show(imageView2,contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
            }
        });

    }
    int imgIndex=0;
    int angle=0;

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();
        List<Photos> photos= ImageManager.getImages();
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
                photos=ImageManager.getImages();
                imgIndex=0;
                Image image1 = new Image(photos.get(0).getiURL());
                textFieldID.setText(String.valueOf(photos.get(0).getId()));
                textFieldName.setText(photos.get(0).getiName());
                textFieldCategory.setText(photos.get(0).getiCategory());
                textFieldLong.setText(String.valueOf(photos.get(0).getiLong()));
                textFieldLat.setText(String.valueOf(photos.get(0).getiLat()));
                textFieldDate.setText(String.valueOf(photos.get(0).getDate()));
                imageView2.setImage(image1);
                break;
            case ("Delete"):
                ImageManager.deleteImage(photos.get(imgIndex).getId());
                textFieldID.setText(" ");
                textFieldName.setText(" ");
                textFieldCategory.setText(" ");
                imageView2.setImage(null);
                break;
            case(">>"):
                imgIndex+=1;
                image1= new Image(photos.get(imgIndex).getiURL());
                textFieldID.setText(String.valueOf(photos.get(imgIndex).getId()));
                textFieldName.setText(photos.get(imgIndex).getiName());
                textFieldCategory.setText(photos.get(imgIndex).getiCategory());
                textFieldLong.setText(String.valueOf(photos.get(imgIndex).getiLong()));
                textFieldLat.setText(String.valueOf(photos.get(imgIndex).getiLat()));
                textFieldDate.setText(String.valueOf(photos.get(imgIndex).getDate()));
                imageView2.setImage(image1);
                ShowImagesController();
                 break;
            case("<<"):
                imgIndex -= 1;
                image1 = new Image(photos.get(imgIndex).getiURL());
                textFieldID.setText(String.valueOf(photos.get(imgIndex).getId()));
                textFieldName.setText(photos.get(imgIndex).getiName());
                textFieldCategory.setText(photos.get(imgIndex).getiCategory());
                textFieldLong.setText(String.valueOf(photos.get(imgIndex).getiLong()));
                textFieldLat.setText(String.valueOf(photos.get(imgIndex).getiLat()));
                textFieldDate.setText(String.valueOf(photos.get(imgIndex).getDate()));
                imageView2.setImage(image1);
                break;
//            case("Rotate 90"):
//                angle+=90;
//                PhotoRotation.rotate(String.valueOf(photos.get(imgIndex).getiURL()),angle);
//                image1 = new Image(photos.get(imgIndex).getiURL());
//                imageView2.setImage(image1);
            }
    }
}