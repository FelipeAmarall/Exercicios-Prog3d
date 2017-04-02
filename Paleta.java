import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Felipe on 28/03/2017.
 */
public class Paleta{
    public static BufferedImage EGA(BufferedImage img) {
        BufferedImage out = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color corpixel = new Color(img.getRGB(x, y));

                int r = corpixel.getRed();
                int g = corpixel.getGreen();
                int b = corpixel.getBlue();

                //R
                if (r < 45)
                    r = 0;
                else if (r > 45 && r <130)
                r = 85;
                else if (r > 130 && r <215)
                r = 171;
                else
                r = 255;

                //G
                if (g < 45)
                    g = 0;
                else if (g > 45 && g <130)
                g = 85;
                else if (g > 130 && g  <215)
                g = 171;
                else
                g = 255;

                //B
                if (b < 45)
                    b = 0;
                else if (b > 45 && b <130)
                b = 85;
                else if (b > 130 && b <215)
                b = 171;
                else
                b = 255;

                Color corpixel2 = new Color(r, g, b);

                out.setRGB(x, y, corpixel2.getRGB());
            }
        }
        return out;
    }
    public static void main(String[] args) throws IOException {
       BufferedImage img = ImageIO.read(new File("C:\\Users\\Felipe\\Documents\\Faculdade\\Prog3D\\img\\cor\\puppy.png"));

       BufferedImage img2 = EGA(img);

       ImageIO.write(img2, "png", new File("C:\\Users\\Felipe\\Documents\\Faculdade\\Prog3D\\img\\cor\\puppy64.png"));

       System.out.println("Fim");
    }
}
