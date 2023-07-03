package com.kimmich;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author ：Kimmich
 * @date ：Created in 2023/7/3 17:23
 */
public class QRCodeDisplay extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String data = "Hello, this is a QR code example!";
        int width = 300;
        int height = 300;

        ImageView imageView = new ImageView(generateQRCodeImage(data, width, height));
        VBox vbox = new VBox(imageView);

        Scene scene = new Scene(vbox, width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("QR Code Display");
        primaryStage.show();
    }

    private Image generateQRCodeImage(String data, int width, int height) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return new Image(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}