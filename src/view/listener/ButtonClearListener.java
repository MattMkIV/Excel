package view.listener;

import view.MyJTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementa l'evento del bottone di reset delle celle.
 *
 * @author Mattia Lazzarini
 * @see java.awt.event.ActionListener
 */
public class ButtonClearListener implements ActionListener {
    /**
     * MyJTable {@link view.MyJTable}.
     */
    private MyJTable table;

    /**
     * Salva una copia della JTable.
     *
     * @param table JTable
     */
    public ButtonClearListener(MyJTable table) {
        this.table = table;
    }

    /**
     * Se è selezionata una cella o più celle della JTable ne cancella il contenuto riportandolo di tipo GeneralCell.
     *
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int rowsSelected = table.getSelectedRowCount();
        int colsSelected = table.getSelectedColumnCount();
        int r[] = table.getSelectedColumns();
        int c[] = table.getSelectedRows();

        if (rowsSelected != 0 && colsSelected != 0)
            for (int i = rowsSelected - 1; i >= 0; i--)
                for (int j = colsSelected - 1; j >= 0; j--)
                    table.setValueAt("", c[i], r[j]);
    }
}
