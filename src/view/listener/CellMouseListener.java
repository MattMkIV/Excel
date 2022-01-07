package view.listener;

import controller.DataStructure;
import model.IntegerCell;
import model.OperationCell;
import model.StringCell;
import view.TopPanel;

import javax.swing.*;
import java.awt.event.*;

public class CellMouseListener implements MouseListener {
    private JTable table;
    private TopPanel topPanel;
    private DataStructure data;

    public CellMouseListener(JTable table, DataStructure data, TopPanel topPanel) {
        this.table = table;
        this.topPanel = topPanel;
        this.data = data;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        topPanel.getInfoBox().setText((char) ('A' + table.getSelectedColumn()) + Integer.toString(table.getSelectedRow() + 1));

        if (e.getClickCount() == 1) {
            String buffer = String.valueOf(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));

            if (!buffer.equals("null"))
                topPanel.getViewBox().setText(buffer);
        }

        if (data.getMatrix().get(table.getSelectedRow()).get(table.getSelectedColumn()) instanceof OperationCell) {
            topPanel.getViewBox().setText(data.getMatrix().get(table.getSelectedRow()).get(table.getSelectedColumn()).getFormula());
        }

        if (e.getClickCount() == 1) {
            if (data.getMatrix().get(table.getSelectedRow()).get(table.getSelectedColumn()) instanceof IntegerCell) {
                System.out.println("SELEZIONATA CELLA DI TIPO INTERO");
            }
            if (data.getMatrix().get(table.getSelectedRow()).get(table.getSelectedColumn()) instanceof OperationCell) {
                System.out.println("SELEZIONATA CELLA DI TIPO OPERAZIONE");
            }
            if (data.getMatrix().get(table.getSelectedRow()).get(table.getSelectedColumn()) instanceof StringCell) {
                System.out.println("SELEZIONATA CELLA DI TIPO STRINGA");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
