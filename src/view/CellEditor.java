package view;

import controller.DataStructure;
import model.OperationCell;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class CellEditor extends DefaultCellEditor {
    private DataStructure data;

    public static CellEditor make(DataStructure data, JTextField viewBox) {
        JTextField field = new JTextField();
        field.getDocument().addDocumentListener(new MyDocumentListener(viewBox));
        return new CellEditor(field, data);
    }

    public CellEditor(JTextField field, DataStructure data) {
        super(field);
        this.data = data;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (data.getMatrix().get(row).get(column) instanceof OperationCell)
            return super.getTableCellEditorComponent(table, ((OperationCell) data.getMatrix().get(row).get(column)).getFormula(), isSelected, row, column);
        else
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    private static class MyDocumentListener implements DocumentListener {
        private JTextField viewBox;

        public MyDocumentListener(JTextField viewBox) {
            this.viewBox = viewBox;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                viewBox.setText(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

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
