package presentation.Components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/*
Author: Nam Ha Minh
Source: https://www.codejava.net/java-se/swing/jtable-column-header-custom-renderer-examples
 */
/**
 * This is the class SimpleHeaderRenderer, this class enables access to a SimpleHeader Renderer in multiple views.
 * Implements the functions of an Icon.
 *
 * @author Pol Valero, Oriol Centeno , Adrià Estevam, Joaquim Balletbo and Manel Martos
 * @version 1.0
 */
public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
    Font titols = new Font("Trebuchet MS", Font.PLAIN, 30);
    private final Color negre = new Color(48,48,48);


    /**
     * Constructor to create a SimpleHeaderRenderer
     * Creates the Simple Header Renderer.
     *
     */
    public SimpleHeaderRenderer() {
        setFont(titols);
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(negre);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    /**
     *
     * getTableCellRendererComponent override allows to configure the specific table we want
     * @param table the table were the information will be shown
     * @param value the value of the objects in  the table
     * @param isSelected
     * @param hasFocus
     * @param row number of rows
     * @param column number of columns
     * @return the configured table
     */
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
