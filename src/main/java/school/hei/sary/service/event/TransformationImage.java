package school.hei.sary.service.event;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TransformationImage {

    public static BufferedImage convertToBlackAndWhite(byte[] img) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage blackAndWhiteImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(img.getRGB(x, y));
                int colAverage = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                Color newColor = new Color(colAverage, colAverage, colAverage);
                blackAndWhiteImg.setRGB(x, y, newColor.getRGB());
            }
        }

        return blackAndWhiteImg;
    }

    public static void main(String[] args) {
        try {

            BufferedImage inputImage = ImageIO.read(new File("input_image.jpg"));


            BufferedImage outputImage = convertToBlackAndWhite(inputImage);


            File output = new File("output_black_and_white.jpg");
            ImageIO.write(outputImage, "jpg", output);

            System.out.println("Conversion terminée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
