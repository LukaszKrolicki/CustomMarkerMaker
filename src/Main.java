import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        try {
            // Load images
            BufferedImage marker = ImageIO.read(new File("C:\\Users\\X\\IdeaProjects\\markerGenerator\\Images\\marker2.png"));
            BufferedImage picture = ImageIO.read(new File("C:\\Users\\X\\IdeaProjects\\markerGenerator\\Images\\inserted.jpg"));

            // Resize picture to 10x10
            BufferedImage resizedPicture = resizeImage(picture, 35, 36);

            // Round corners of the resized picture
            BufferedImage roundedPicture = roundCorners(resizedPicture, 28);

            // Combine picture with marker
            BufferedImage combinedImage = combineImages(marker, roundedPicture);

            // Save the combined image
            File outputFile = new File("C:\\Users\\X\\IdeaProjects\\markerGenerator\\Images\\generated.png");
            ImageIO.write(combinedImage, "png", outputFile);

            System.out.println("Combined image saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Resize image to specified width and height
    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    // Round the corners of the image
    private static BufferedImage roundCorners(BufferedImage image, int cornerRadius) {
        BufferedImage roundedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = roundedImage.createGraphics();
        g2.setClip(new RoundRectangle2D.Double(0, 0, image.getWidth(), image.getHeight(), cornerRadius, cornerRadius));
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return roundedImage;
    }

    // Combine two images
    private static BufferedImage combineImages(BufferedImage background, BufferedImage overlay) {
        BufferedImage combined = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = combined.createGraphics();
        g2.drawImage(background, 0, 0, null);
        g2.drawImage(overlay, 2, 2, null);
        g2.dispose();
        return combined;
    }
}
