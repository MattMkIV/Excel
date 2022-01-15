package view;

import controller.MyTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Imposta graficamente il componente che visualizza gli indici di riga.
 *
 * @author Mattia Lazzarini
 */
public class RowHeader {
    /**
     * {@link JList}.
     */
    private final JList rowHeader;

    /**
     * Inizializza una JList e ne imposta la grafica adattandosi al TableHeader della JTable.
     *
     * @param myTableMode TableModel dai cui prendere il Vector di interi
     * @param table       MyJTable
     */
    public RowHeader(MyTableModel myTableMode, MyJTable table) {

        rowHeader = new JList(myTableMode.getVector());
        rowHeader.setFixedCellHeight(table.getRowHeight());
        rowHeader.setFont(table.getTableHeader().getFont());
        rowHeader.setBackground(table.getTableHeader().getBackground());
        rowHeader.setForeground(table.getTableHeader().getForeground());
        rowHeader.setSelectionBackground(new Color(0, 0, 0, 0));
        rowHeader.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

        DefaultListCellRenderer render = (DefaultListCellRenderer) (rowHeader.getCellRenderer());
        render.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Ritorna la JList da poter usare come componente per gli indici di riga.
     *
     * @return JList
     */
    public JList getRowHeader() {
        return rowHeader;
    }
}
