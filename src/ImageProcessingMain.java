import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.util.Scanner;

public class ImageProcessingMain {

    public static void main(String[] args) {
        try {
            // Let the user choose the image file
            File selectedFile = chooseImageFile();
            if (selectedFile == null) {
                System.out.println("No file selected. Exiting.");
                return;
            }

            // Load the selected image
            BufferedImage image = ImageIO.read(selectedFile);

            // Convert the image to grayscale
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int rgb = image.getRGB(i, j);
                    int red = (rgb >> 16) & 0xff;
                    int green = (rgb >> 8) & 0xff;
                    int blue = rgb & 0xff;
                    int weightedGray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                    Color grayColor = new Color(weightedGray, weightedGray, weightedGray);
                    image.setRGB(i, j, grayColor.getRGB());
                }
            }

            // Save the grayscale image
            ImageIO.write(image, "jpg", new File("image_grayscale.jpg"));

            // Apply threshold filter
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color color = new Color(image.getRGB(i, j));
                    int threshold = 128;
                    int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    int newColor = (gray < threshold) ? 0 : 255;
                    Color filteredColor = new Color(newColor, newColor, newColor);
                    image.setRGB(i, j, filteredColor.getRGB());
                }
            }

            // Resize the image
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new width: ");
            int newWidth = scanner.nextInt();
            System.out.print("Enter new height: ");
            int newHeight = scanner.nextInt();
            Image resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = ((BufferedImage) resizedImage).createGraphics();
            g.drawImage(image, 0, 0, newWidth, newHeight, null);
            g.dispose();

            // Save the resized image
            ImageIO.write((BufferedImage) resizedImage, "jpg", new File("image_resized.jpg"));

            // Edge detection using Sobel operator
            int[][] sobelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
            int[][] sobelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
            for (int i = 1; i < image.getWidth() - 1; i++) {
                for (int j = 1; j < image.getHeight() - 1; j++) {
                    int gx = applyFilter(i, j, sobelX, image);
                    int gy = applyFilter(i, j, sobelY, image);
                    int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                    magnitude = Math.min(255, Math.max(0, magnitude));
                    Color edgeColor = new Color(magnitude, magnitude, magnitude);
                    image.setRGB(i, j, edgeColor.getRGB());
                }
            }

            // Save the final edge-detected image
            ImageIO.write(image, "jpg", new File("image_edges.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to choose an image file using JFileChooser
    private static File chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    // Function to apply a filter to a pixel
    private static int applyFilter(int x, int y, int[][] filter, BufferedImage image) {
        int sum = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Color color = new Color(image.getRGB(x + i, y + j));
                sum += color.getRed() * filter[i + 1][j + 1];
            }
        }
        return sum;
    }
}
