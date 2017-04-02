import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Felipe on 02/04/2017.
 */
public class Histograma {
    public static final String PATH = "C:/Users/Felipe/Documents/Faculdade/Prog3D/img/gray";

    int[] CalcularHistograma(BufferedImage img){
        int[] histograma = new int[256];

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));
                int red = cor.getRed();
                histograma[red] += 1;
            }
        }
        return histograma;
    }

    public int[] CalcularHistogramaAcum(int[] histograma){

        int[] acumulado = new int[256];
        acumulado[0] = histograma[0];

        for (int i = 1;i < histograma.length; i++){
            acumulado[i] = histograma[i] + acumulado[i-1];
        }
        return acumulado;
    }

    private int menorValor(int[] histograma){
        for(int i = 0; i < histograma.length; i++){
            if (histograma[i] !=0){
                return i;
            }
        }
        return 0;
    }

    int[] CalcularColorMap(int[] histograma, int pixels){
        int[] ColorMap = new int[256];
        int[] acumulado = CalcularHistogramaAcum(histograma);
        float menor = menorValor(histograma);

        for(int i = 0;i<histograma.length; i++){
            ColorMap[i] = Math.round(((acumulado[i] - menor) / (pixels - menor)) * 255);
        }

        return ColorMap;
    }

    BufferedImage Equalize(BufferedImage img){
        int[] histograma = CalcularHistograma(img);
        int[] ColorMap = CalcularColorMap(histograma, img.getWidth() * img.getHeight());
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));
                int tom = cor.getRed();

                int newTom = ColorMap[cor.getRed()];

                Color newCor = new Color(newTom, newTom, newTom);

                out.setRGB(x, y, newCor.getRGB());
            }
        }
        return out;
    }

    void run() throws IOException
    {
        BufferedImage img = ImageIO.read(new File(PATH, "university.png"));
        BufferedImage img2 = Equalize(img);
        ImageIO.write(img2, "png", new File(PATH, "equalizeduniversity.png"));
    }

    public static void main(String[] args) throws IOException {
        new Histograma().run();
    }
}
