package view;

import controller.MyTableModel;
import model.OperationCell;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyJTable extends JTable {
    private MyTableModel myTableModel;
    private JTextField viewBox;

    public MyJTable(MyTableModel myTableModel, JTextField viewBox) {
        this.myTableModel = myTableModel;
        this.viewBox = viewBox;

        setRowHeight(22);
        setShowGrid(true);
        setFocusable(false);
        setModel(myTableModel);
        setCellSelectionEnabled(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        setSelectionBackground(new Color(0, 0, 0, 45));
    }


    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Boolean isSelected = false;
        Boolean hasFocus = false;

        if(!isPaintingForPrint()) {
            isSelected = super.isCellSelected(row, col);
            boolean rowIsLead = (selectionModel.getLeadSelectionIndex() == row);
            boolean colIsLead = (columnModel.getSelectionModel().getLeadSelectionIndex() == col);
            hasFocus = (rowIsLead && colIsLead) && super.isFocusOwner();
        }

        Object value = super.getValueAt(row, col);
        JComponent cellRenderer = (JComponent) renderer.getTableCellRendererComponent(this, value, isSelected, hasFocus, row, col);

        if(isSelected) {    //sia selezionata sia con focus
            if(((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col) instanceof OperationCell)
                viewBox.setText(((OperationCell) ((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col)).getFormula());
            else {

                viewBox.setText(value.toString());
            }
        }

        return cellRenderer;
    }
}
