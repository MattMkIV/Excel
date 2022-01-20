package view.listener;

import controller.DataStructure;
import controller.FileOpener;
import controller.MyTableModel;
import view.MyJTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Implementa l'evento per il recupero del file temporaneo.
 * @author Mattia Lazzarini
 * @see ActionListener
 */
public class ButtonRestoreListener implements ActionListener {
    /**
     * MyJTable {@link MyJTable}.
     */
    private MyJTable table;
    /**
     * Struttura dati {@link DataStructure}.
     */
    private DataStructure data;
    /**
     * TabelModel {@link MyTableModel}.
     */
    private MyTableModel myTableModel;

    /**
     * Salva la JTable, la struttura dati e la TableModel.
     * @param table MyJTable
     * @param data DataStructure
     * @param myTableModel MyTableModel
     */
    public ButtonRestoreListener(MyJTable table, DataStructure data, MyTableModel myTableModel) {
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
        new FileOpener(table, data, myTableModel, new File(System.getProperty("java.io.tmpdir") + File.separator + "ProjectTmp.epog"));
    }
}
