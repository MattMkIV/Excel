package view.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementa il comportamento del bottone di reset.
 */
public class ButtonClearListener implements ActionListener {
    private JTable table;

    /**
     * Salva una copia per riferimento della JTable.
     * @param table JTable
     */
    public ButtonClearListener(JTable table) {
        this.table = table;
    }

    /**
     * Se è selezionata una cella della JTable allora ne cancella il contenuto riportando la cella di tipo GeneralCell.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int rowsSelected = table.getSelectedRowCount();
        int colsSelected = table.getSelectedColumnCount();
        int r[] = table.getSelectedColumns();
        int c[] = table.getSelectedRows();
        //int rowSelected = table.getSelectedRow();
        //int colSelected = table.getSelectedColumn();

        if(rowsSelected != 0 && colsSelected != 0) {
            for(int i=rowsSelected-1; i>=0; i--) {
                for(int j=colsSelected-1; j>=0; j--) {
                    System.out.println(c[i] + " " + r[j]);
                    table.setValueAt("", c[i], r[j]);
                }
            }
        }
        /*if (!(rowSelected < 0 && colSelected < 0))
            table.setValueAt("", table.getSelectedRow(), table.getSelectedColumn());*/


    }
}
