package com.kimmich;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Kimmich
 * @date ：Created in 2023/7/3 17:31
 */
public class QRCodeGenerator {

    public static void main(String[] args) {
        String url = "E:/WNXY/1.jpg";
        String filePath = "E:/imig/outputQRCode.png";
        int width = 300;
        int height = 300;

        generateQRCode(url, filePath, width, height);
    }

    public static void generateQRCode(String url, String filePath, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            Path path = Paths.get(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR code generated successfully!");
        } catch (Exception e) {
            System.err.println("Error while generating QR code: " + e.getMessage());
        }
    }
}