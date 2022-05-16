package presentation.views;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/*
Author: Nam Ha Minh
Source: https://www.codejava.net/java-se/swing/jtable-column-header-custom-renderer-examples
 */

public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
    Font titols = new Font("Trebuchet MS", Font.PLAIN, 30);
    private final Color negre = new Color(48,48,48);


    public SimpleHeaderRenderer() {
        setFont(titols);
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(negre);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        setText(value.toString());
        return this;
    }

}
