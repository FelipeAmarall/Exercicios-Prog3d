/**
 * Created by Felipe on 28/03/2017.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pixels {

    public static BufferedImage pixelate(BufferedImage img, int pixelsize)
    {
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img.getHeight(); y += pixelsize) {
            for (int x = 0; x < img.getWidth(); x += pixelsize) {

               for (int py = 0; py < pixelsize; py++) {
                    for(int px = 0;px < pixelsize; px++) {
                        Color cor2 = new Color(img.getRGB(x, y));
                        out.setRGB(px + x, py + y, cor2.getRGB());
                    }
                }
            }
        }
        return out;
    }

    public static BufferedImage Contraste(BufferedImage img, int nivel) {
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));

                int r = cor.getRed();
                int g = cor.getGreen();
                int b = cor.getBlue();

                Color cor2 = new Color(r, g, b);

                out.setRGB(x, y, cor2.getRGB() + nivel);
            }
        }
        return out;
    }

    public static BufferedImage Blur(BufferedImage img)
    {

    }

    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Felipe\\Documents\\Faculdade\\Prog3D\\img\\cor\\puppy.png"));

        BufferedImage img2 = pixelate(img, 10);
         BufferedImage img3 = Contraste(img2, 20);

        ImageIO.write(img2, "png", new File("C:\\Users\\Felipe\\Documents\\Faculdade\\Prog3D\\img\\cor\\pixelpuppy.png"));
        ImageIO.write(img3, "png", new File("C:\\Users\\Felipe\\Documents\\Faculdade\\Prog3D\\img\\cor\\pixelcontrastepuppy.png"));

        System.out.println("Fim");
    }


}
