package presentation.Components;

import javax.swing.*;
import java.awt.*;

public class RowIcon implements Icon {

    private int width;
    private int height;
    private Color color;

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

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        g.setColor(color);
        g.drawRoundRect(x,y,width,height,50,50);
        g.fillRoundRect(x,y,width,height,50,50);

    }
}