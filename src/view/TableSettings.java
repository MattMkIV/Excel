package view;

import controller.DataStructure;
import controller.MyTableModel;
import view.listener.TableListener;

import javax.swing.*;
import java.awt.*;

/**
 * Gestione della JTable.
 */
public class TableSettings extends JPanel {
    private DataStructure data;
    private MyTableModel myTableModel;
    private JTable table;

    /**
     * Vengono impostati i valori grafici della JTable e inizializzati i valori della struttura dati.
     */
    public TableSettings() {
        data = new DataStructure();
        myTableModel = new MyTableModel(data);
        table = new JTable(myTableModel);

        table.setRowHeight(22);
        table.setShowGrid(true);
        table.setCellSelectionEnabled(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionBackground(new Color(0, 0, 0, 45));

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
