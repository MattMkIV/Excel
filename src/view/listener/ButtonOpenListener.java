package view.listener;

import controller.DataStructure;
import controller.FileOpener;
import controller.MyTableModel;
import view.MyJTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementa l'evento per l'apertura di una file salvato.
 * @author Mattia Lazzarini
 * @see java.awt.event.ActionListener
 */
public class ButtonOpenListener implements ActionListener {
    /**
     * JTable {@link MyJTable}.
     */
    private MyJTable table;
    /**
     * Struttura dati {@link DataStructure}.
     */
    private DataStructure data;
    /**
     * TableModel {@link MyTableModel}.
     */
    private MyTableModel myTableModel;

    /**
     * Salva una copia della Jtable, della struttura dati e della TableModel.
     * @param table MyJTable
     * @param data DataStructure
     * @param myTableModel MyTableModel
     */
    public ButtonOpenListener(MyJTable table, DataStructure data, MyTableModel myTableModel) {
        this.table = table;
        this.data = data;
        this.myTableModel = myTableModel;
    }

    /**
     * Crea un oggetto {@link FileOpener}.
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new FileOpener(this.table, this.data, this.myTableModel);
    }
}
