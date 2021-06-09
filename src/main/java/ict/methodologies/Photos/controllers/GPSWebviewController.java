package ict.methodologies.Photos.controllers;
import ict.methodologies.Photos.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ict.methodologies.Photos.Models.Photos;
import ict.methodologies.Photos.PhotosApplication;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class GPSWebviewController {
    @FXML
    private WebView webView;
    int imageId;
    public void setImageId(int id) {
        this.imageId = id;
    }
    @FXML
    private void initialize()
    {
      ImageManager.getImage(imageId);
            String html = null;
            html=("""
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                    \t<meta charset="UTF-8">
                    \t<title>A Basic Map</title>
                    \t<style>
                    \t\t#map {
                    \t\t\theight: 100%;
                    \t\t}
                    \t\t/* Optional: Makes the sample page fill the window. */
                    \t\thtml, body {
                    \t\t\theight: 100%;
                    \t\t\tmargin: 0;
                    \t\t\tpadding: 0;
                    \t\t}
                    \t</style>
                    \t<script>
                    \tfunction initMap() {
                    \t\tvar mapOptions = {
                    \t\t\tzoom: 8,""");
            html+=("center: new google.maps.LatLng("+ImageManager.getImageLong()+","+ImageManager.getImageLat()+"),");
            html+= "mapTypeId: 'roadmap'\n" +
                    "\t\t};\n" +
                    "\t\tvar map = new google.maps.Map(document.getElementById('map'), mapOptions);\n" +
                    "\t\tvar PhotoLocation = {lat: "+ImageManager.getImageLong()+",lng: "+ImageManager.getImageLat()+"};\n" +
                    "\t\tvar marker = new google.maps.Marker();\n" +
                    "\t\tvar marker = new google.maps.Marker({\n" +
                    "\t\t\tposition: PhotoLocation,\n" +
                    "\t\t\tmap: map,\n" +
                    "\t\t\ttitle: 'Photo Location'\n" +
                    "\t\t\t});\n" +
                    "\t}\n" +
                    "\t</script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div id=\"map\"></div>\n" +
                    "<script async defer\n" +
                    "\t\tsrc=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyCjQDtg-eFviCMYatsPupihC3UAavqR46k&callback=initMap\">\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>";
        System.out.println(html);
         webView.getEngine().loadContent(html);
    }
}
