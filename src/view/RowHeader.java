package view;

import controller.MyTableModel;

import javax.swing.*;

public class RowHeader {
    private JList rowHeader;

    public RowHeader(MyTableModel myTableMode, JTable table) {

        rowHeader = new JList(myTableMode.getVector());
        rowHeader.setFixedCellHeight(table.getRowHeight());
        rowHeader.setFont(table.getTableHeader().getFont());
        rowHeader.setBackground(table.getTableHeader().getBackground());
        rowHeader.setForeground(table.getTableHeader().getForeground());
        rowHeader.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

        DefaultListCellRenderer render = (DefaultListCellRenderer) (rowHeader.getCellRenderer());
        render.setHorizontalAlignment(JLabel.CENTER);
    }

    public JList getRowHeader() {
        return rowHeader;
    }
}
