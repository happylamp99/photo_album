package ict.methodologies.Photos.controllers;
import ict.methodologies.Photos.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ict.methodologies.Photos.Models.Photos;
import ict.methodologies.Photos.PhotosApplication;
import java.io.FileWriter;
import java.io.IOException;

public class GPSWebviewController {
    @FXML
    private WebView webView;

    @FXML
    private void initialize()
    {
        ImageManager.getImage(imageId);
            String html = null;
       //     FileWriter myWriter = new FileWriter(this.getClass().getResource("/").getPath());
            html=("<!DOCTYPE html>");
            html+=("<html>");
            html+=("<head>");
            html+=("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />");
            html+=("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
            html+=("<style>html,body{height:100%;margin:0;padding:0;}#map_canvas{height:100%;}</style>");
            html+=("<script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?sensor=false\"></script>");
            html+=("<script type=\"text/javascript\">");
            html+=("function initialise() {");
            html+=("    var options = { zoom:2, mapTypeId:google.maps.MapTypeId.ROADMAP, center:new google.maps.LatLng(0.0, 0.0)};");
            html+=("    var map = new google.maps.Map(document.getElementById('map_canvas'), options);");
            html+=("    var marker;");

                final String fullPath = ImageManager.getImageURL().substring(6).trim().replace("\\", "\\\\");

            html+=("    marker = new google.maps.Marker({");
            html+=("        position:new google.maps.LatLng("+ ImageManager.getImageLat() +ImageManager.getImageLong()+"),");
            html+=("        map:map,");
            html+=("        title:\"" + fullPath + "\"});");
            html+=("    google.maps.event.addListener(marker, 'click', function() { document.location = \"" + fullPath + "\"; });");

            html+=("}");
            html+=("</script>");
            html+=("</head>");
            html+=("<body onload=\"initialise()\">");
            html+=("<div id=\"map_canvas\"></div>");
            html+=("</body>");
            html+=("</html>");
        //Load html

        WebEngine engine = webView.getEngine();
        engine.load(html);
    }
    public void setImageId(int id) {
        this.imageId = id;
    }
    int imageId;
}
