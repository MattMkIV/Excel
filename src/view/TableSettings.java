package view;

import controller.DataStructure;
import controller.MyTableModel;

import javax.swing.*;

/**
 * Gestione del JPanel contentente la MyJTable.
 * @author Mattia Lazzarini
 * @see JPanel
 */
public class TableSettings extends JPanel {
    /**
     * Struttura dati {@link DataStructure}.
     */
    private DataStructure data;
    /**
     * TableModel {@link MyTableModel}.
     */
    private MyTableModel myTableModel;
    /**
     * MyJTable {@link MyJTable}.
     */
    private MyJTable table;

    /**
     * Vengono inizializzate le classi contenenti la struttura dati, il TableModel e la MyJTable.
     * @param viewBox JTextField con cui l'utente può interagire
     * @see MyTableModel
     * @see MyJTable
     * @see DataStructure
     */
    public TableSettings(JTextField viewBox) {
        data = new DataStructure();
        myTableModel = new MyTableModel(data);
        table = new MyJTable(myTableModel, viewBox);

        add(table);
    }

    /**
     * Ritorna la MyJTable.
     * @return MyJTable
     */
    public MyJTable getTable() {
        return table;
    }

    /**
     * Ritorna la classe contenente la struttura dati.
     * @return Classe contenente la struttura dati
     */
    public DataStructure getData() {
        return data;
    }

    /**
     * Ritorna il Model della JTable.
     * @return Modello della JTable
     */
    public MyTableModel getMyTableModel() {
        return myTableModel;
    }
}
