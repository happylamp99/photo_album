package ict.methodologies.Photos.controllers;

//import ict.methodologies.Photos.Editor.PhotoRotation;
import ict.methodologies.Photos.Editor.PhotoRotation;
import ict.methodologies.Photos.ImageManager;
import ict.methodologies.Photos.Models.Photos;
import ict.methodologies.Photos.PhotosApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    @FXML
    private TextField textFieldSearch;

    @FXML
    private GridPane gridPane;

    String albumName;

    public void ShowImagesController(){
        ContextMenu contextMenu = new ContextMenu();
        Menu item1 = new Menu("Add to Album");
        MenuItem sub1 = new MenuItem("New Album");
        MenuItem sub2 = new MenuItem("Existing Album");
        item1.getItems().addAll(sub1,sub2);
        MenuItem item2 = new MenuItem("Show Metadata");
        MenuItem item3 = new MenuItem("Delete Image");
        MenuItem item4 = new MenuItem("Show GPS location");


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
        item4.setOnAction((ActionEvent e) -> {
            try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GPSlocation.fxml"));
            loader.setControllerFactory(GPSWebviewController -> new GPSWebviewController(Integer.parseInt(textFieldID.getText())));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        });
        contextMenu.getItems().addAll(item1,item2,item3,item4);

        imageView2.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                contextMenu.show(imageView2,contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
            }
        });
    }
    int imgIndex=0;
    int angle=0;
    int[][] gridId = new int[16][16];

    public void clickGrid(javafx.scene.input.MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridPane) {
            Node parent = clickedNode.getParent();
            while (parent != gridPane) {
                clickedNode = parent;
                parent = clickedNode.getParent();
            }
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);

            textFieldID.setText(String.valueOf(gridId[colIndex][rowIndex]));
            ImageManager.getImage(gridId[colIndex][rowIndex]);
            Image image = new Image(ImageManager.getImageURL());
            imageView2.setImage(image);
            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
        }
    }


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
            case("Search"):
                List<Integer> ids= ImageManager.searchImages(textFieldSearch.getText());
                gridPane.getChildren().clear();

                int imageCol = 0;
                int imageRow = 0;

                for(int i=0;i< ids.size();i++){

                    gridId[imageCol][imageRow] = ids.get(i);
                    ImageManager.getImage(ids.get(i));
                    HBox hb = new HBox(20);
                    Image image1 = new Image(ImageManager.getImageURL());

                    ImageView imageViewG = new ImageView();
                    imageViewG.setFitHeight(100);
                    imageViewG.setFitWidth(100);
                    imageViewG.setImage(image1);
                    hb.getChildren().add(imageViewG);
                    gridPane.add(hb,imageCol,imageRow);

                    imageCol++;
                    if(imageCol > 4){
                        imageCol=0;
                        imageRow++;
                    }
                }
               break;

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
                ShowImagesController();
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
                ShowImagesController();
                break;
            case("Rotate 90"):
                angle+=90;
                PhotoRotation rotation = new PhotoRotation();
                rotation.rotate(photos.get(imgIndex).getiURL(),angle);
                image1 = new Image(photos.get(imgIndex).getiURL());
                imageView2.setImage(image1);
                angle=0;
                break;
            }
    }
}