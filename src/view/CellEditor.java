package view;

import controller.DataStructure;
import model.OperationCell;

import javax.swing.*;
import java.awt.*;

public class CellEditor extends DefaultCellEditor {
    private DataStructure data;

    public static CellEditor make(DataStructure data) {
        JTextField field = new JTextField();
        return new CellEditor(field, data);
    }

    public CellEditor(JTextField textField, DataStructure data) {
        super(textField);
        this.data = data;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (data.getMatrix().get(row).get(column) instanceof OperationCell)
            return super.getTableCellEditorComponent(table, data.getMatrix().get(row).get(column).getFormula(), isSelected, row, column);
        else
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
