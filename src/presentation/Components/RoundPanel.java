package presentation.Components;

import javax.swing.*;
import java.awt.*;

/**
 * This is the class  RoundPanel, this class enables access to a  Round Panel in multiple views.
 * Extends the functions of a JPanel.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class RoundPanel extends JPanel {
    /**
     * paintComponent is a method that reshapes the JPanel to be round
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(123, 212, 212));
        g2d.drawRect(10, 15, 90, 60);


        g2d.setColor(new Color(31, 21, 1));
        g2d.fillRect(250, 195, 90, 60);

    }
}
