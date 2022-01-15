package view;

import controller.DataStructure;
import model.OperationCell;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;

/**
 * Implementa il comportamento particolare che una cella deve avere se di tipo operazione.
 * <p>Se si sta per modificare una cella di tipo operazione viene mostrata la formula nella cella e non il
 * risultato così da poter essere modificata direttamente dall'utente.</p>
 *
 * @author Mattia Lazzarini
 * @see DefaultCellEditor
 */
public class CellEditor extends DefaultCellEditor {
    /**
     * {@link DataStructure}
     */
    private DataStructure data;

    /**
     * Inizializza come final la JTextField che deve essere mostrata e aggiunge il listener.
     *
     * @param data    Struttura dati {@link DataStructure}
     * @param viewBox Componente che mostra il contenuto dinamico {@link TopPanel}
     * @return {@link CellEditor}
     */
    public static CellEditor make(DataStructure data, JTextField viewBox) {
        JTextField field = new JTextField();
        field.getDocument().addDocumentListener(new MyDocumentListener(viewBox));
        return new CellEditor(field, data);
    }

    /**
     * Richiama il costrutture passando il componente final inizializzato in precedenza e salva la {@link DataStructure}.
     *
     * @param field JTextField della cella
     * @param data  Struttura Dati {@link DataStructure}
     */
    public CellEditor(JTextField field, DataStructure data) {
        super(field);
        this.data = data;
    }

    /**
     * In base al tipo di cella che si sta per modificare viene cambiato il contenuto che viene mostrato come modificabile.
     *
     * @param table      {@link JTable}
     * @param value      {@link Object}
     * @param isSelected isSelected
     * @param row        Indice di riga
     * @param column     Indice di colonna
     * @return {@link Component}
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (data.getMatrix().get(row).get(column) instanceof OperationCell)
            return super.getTableCellEditorComponent(table, ((OperationCell) data.getMatrix().get(row).get(column)).getFormula(), isSelected, row, column);
        else
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    /**
     * Aggiorna il contenuto della viewBox in base al contenuto che viene inserito nella cella.
     * <p>Se viene effettuata una modifica al {@link DocumentListener} della cella automaticamente il contenuto della
     * viewBox viene aggiornato.
     *
     * @see DocumentListener
     */
    private static class MyDocumentListener implements DocumentListener {
        /**
         * {@link TopPanel}.
         */
        private JTextField viewBox;

        /**
         * Salva il componente di cui aggiornare il contenuto.
         *
         * @param viewBox JTextField modificabile dall'utente
         */
        public MyDocumentListener(JTextField viewBox) {
            this.viewBox = viewBox;
        }

        /**
         * Aggiorna dinamicamente il contenuto se viene aggiunto un carattere.
         *
         * @param e {@link DocumentEvent}
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                viewBox.setText(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        /**
         * Aggiorna dinamicamente il contenuto se viene rimosso un carattere.
         *
         * @param e {@link DocumentEvent}
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                viewBox.setText(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
}
