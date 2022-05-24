package presentation.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel { //todo
    //hauriem de crear una classe generica que ens permeti crear imatges escalades en comptes de posar funcions independents a cada classe (com es el cas de loginView i signUpView).
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
