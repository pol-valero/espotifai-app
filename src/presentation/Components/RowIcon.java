package presentation.Components;

import javax.swing.*;
import java.awt.*;

/**
 * This is the class   RowIcon, this class enables access to a Row Icon in multiple views.
 * Implements the functions of an Icon.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class RowIcon implements Icon {

    private int width;
    private int height;
    private Color color;

    /**
     * Constructor to create a RowIcon
     * Creates the RowIcon.
     *
     * @param width of the RowIcon created
     * @param height of the RowIcon created
     * @param color of the RowIcon created
     */
    public RowIcon (int width, int height, Color color)
    {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getIconWidth()
    {
        return width;
    }

    public int getIconHeight()
    {
        return height;
    }

    /**
     * paintIcon is a method that paints the desired components if the Icon
     * @param c
     * @param g
     * @param x axes length of the Icon
     * @param y axes length of the Icon
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        g.setColor(color);
        g.drawRoundRect(x,y,width,height,50,50);
        g.fillRoundRect(x,y,width,height,50,50);

    }
}