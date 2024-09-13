package presentation.Components;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * This is the class  RoundButton, this class enables access to a  Round Button in multiple views.
 * Extends the functions of a JButton.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class RoundButton extends JButton {

    /**
     * Constructor to create a RoundButton
     * Creates the RoundButton.
     *
     * @param label label of the rounded button
     *
     */
    public RoundButton(String label) {
        super(label);

        setBackground(Color.lightGray);
        setFocusable(false);

    /*
     These statements enlarge the button so that it
     becomes a circle rather than an oval.
    */
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
        setContentAreaFilled(false);
    }

    /**
     * paintComponent is a method that reshapes the JButton to be round
     * @param g
     */
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }
    /**
     * paintBorder is a method that reshapes the JButton's border to be round
     * @param g
     */
    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Hit detection.
    Shape shape;

    /**
     * contains returns the shape with the specifications of x and y
     * @param x axes length of the button
     * @param y axes length of the button
     * @return the final shape of the rounded button
     */
    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}