/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sifreleme;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class sifreleme {

    public static void main(String[] args) {
        // Ana resim dosyası
        String imagePath = "\"C:\\Users\\Emirhan Guney\\Desktop\\indir.jpg\"";

        // Anahtar değeri
        int key = 42;

        // Resmi matris haline getir
        int[][] originalMatrix = imageToMatrix(imagePath);

        // Matrisi şifrele
        int[][] encryptedMatrix = encryptMatrix(originalMatrix, key);

        // Şifrelenmiş matrisi kaydet
        String encryptedImagePath = "\"C:\\Users\\Emirhan Guney\\Desktop\\Yeni Metin Belgesi (2).txt\"";
        matrixToImage(encryptedMatrix, encryptedImagePath);
    }

    private static int[][] imageToMatrix(String imagePath) {
        try {
            // Resmi yükle
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Resmin genişlik ve yüksekliğini al
            int width = image.getWidth();
            int height = image.getHeight();

            // Matrisi oluştur
            int[][] matrix = new int[height][width];

            // Her pikselin renk değerini matrise ekle
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = image.getRGB(j, i);
                }
            }

            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void matrixToImage(int[][] matrix, String savePath) {
        // Matrisi BufferedImage'a dönüştür
        BufferedImage image = new BufferedImage(matrix[0].length, matrix.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                image.setRGB(j, i, matrix[i][j]);
            }
        }

        try {
            // Resmi kaydet
            ImageIO.write(image, "jpg", new File(savePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] encryptMatrix(int[][] matrix, int key) {
        // Matris üzerinde XOR işlemi uygula
        int[][] encryptedMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                encryptedMatrix[i][j] = matrix[i][j] ^ key;
            }
        }

        return encryptedMatrix;
    }
}
