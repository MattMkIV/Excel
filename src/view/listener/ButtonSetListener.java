package view.listener;

import view.MyJTable;
import view.TopPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementa l'evento per settare il valore nella cella selezionata.
 *
 * @author Mattia Lazzarini
 * @see java.awt.event.ActionListener
 */
public class ButtonSetListener implements ActionListener {
    /**
     * JTable {@link view.MyJTable}
     */
    private MyJTable table;
    /**
     * Componenti del pannello superiore {@link TopPanel}
     */
    private TopPanel panelTop;

    /**
     * Salva la JTable e un oggetto TopPanel.
     *
     * @param panelTop TopPanel
     * @param table    MyJTable
     */
    public ButtonSetListener(TopPanel panelTop, MyJTable table) {
        this.table = table;
        this.panelTop = panelTop;
    }

    /**
     * Prende ciò che è stato inserito nella JTextField e lo mette nella cella selezionata.
     *
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!table.isEditing()) {
            int rowSelected = table.getSelectedRow();
            int colSelected = table.getSelectedColumn();

            if (!(rowSelected < 0 && colSelected < 0)) {
                String buffer = panelTop.getViewBox().getText();

                table.setValueAt(buffer, rowSelected, colSelected);
            }
        }
    }
}
