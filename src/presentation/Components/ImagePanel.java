package presentation.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This is the class ImagePanel, this class enables access to an Image Panel in multiple views.
 * Extends the functions of a JPanel.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class ImagePanel extends JPanel {
    public ImageIcon getImageIcon (String filePath, int wt, int ht){

        Image image = (new ImageIcon(filePath)).getImage();

        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, wt, ht, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }
}
