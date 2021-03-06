package ict.methodologies.Photos.Editor;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class PhotoRotation {
    public void rotate(String filesrc, double angle){
        OpenCV.loadLocally();
        //delete prefix file:// using substring
        Mat src = Imgcodecs.imread(filesrc.substring(6));
        Mat dst = new Mat();

        // Rotate clockwise at 90 degrees
        if (angle == 90 || angle == -270) {
            Core.transpose(src, dst);
            Core.flip(dst, dst, 1);
        }

        // Rotate clockwise at 180 degrees
        else if (angle == 180 || angle == -180)
            Core.flip(src, dst, -1);

            // Rotate clockwise at 270 degrees
        else if (angle == 270 || angle == -90) {
            Core.transpose(src, dst);
            Core.flip(dst, dst, 0);
        }
        // Destination where image is written in
        Imgcodecs.imwrite(filesrc.substring(6), dst);

    }

}