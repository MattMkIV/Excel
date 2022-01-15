package view.listener;

import view.MyJTable;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Implementa il listener di una JTextField per confermare il contenuto inserito al suo interno e inserirlo nella
 * struttura dati se una cella della JTable è selezionata.
 *
 * @author Mattia Lazzarini
 * @see KeyListener
 */
public class TextFieldViewListener implements KeyListener {
    /**
     * MyJTable {@link MyJTable}.
     */
    private MyJTable table;
    /**
     * JTextField per la visualizzazione delle formule.
     */
    private JTextField viewBox;

    /**
     * Salva una copia per riferimento della JTable e del componente di cui si vuole aggiungere il listener.
     *
     * @param table   JTable sul quale compare la modifica
     * @param viewBox Componente su cui applicare il listener
     */
    public TextFieldViewListener(MyJTable table, JTextField viewBox) {
        this.table = table;
        this.viewBox = viewBox;
    }

    /**
     * Controlla il carattere digitato e se equivale al "new line" salva il contenuto del TextField nella matrice.
     *
     * @param e {@link KeyEvent}
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            if (!(table.getSelectedRow() < 0 && table.getSelectedColumn() < 0)) {
                table.setValueAt(viewBox.getText(), table.getSelectedRow(), table.getSelectedColumn());
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
