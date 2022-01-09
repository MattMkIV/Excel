package view;

import controller.DataStructure;
import controller.MyTableModel;
import view.listener.TableListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Gestione della JTable.
 * @author Mattia
 */
public class TableSettings extends JPanel {
    private DataStructure data;
    private MyTableModel myTableModel;
    private MyJTable table;

    /**
     * Vengono impostati i valori grafici della JTable e inizializzati i valori della struttura dati.
     */
    public TableSettings(JTextField viewBox) {
        data = new DataStructure();
        myTableModel = new MyTableModel(data);
        table = new MyJTable(myTableModel, viewBox);



        add(table);
    }

    /**
     * Ritorna la JTable.
     * @return JTable
     */
    public JTable getTable() {
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
