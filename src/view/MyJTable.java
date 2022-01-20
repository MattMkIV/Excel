package view;

import controller.MyTableModel;
import model.IntegerCell;
import model.OperationCell;
import model.StringCell;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Estende la classe {@link JTable} rendendo modificabile la formula se la cella è di tipo formula e setta la JTable.
 *
 * @author Mattia Lazzarini
 * @see JTable
 */
public class MyJTable extends JTable {
    /**
     * JTextField che mostra le informazioni di una cella.
     *
     * @see TopPanel
     */
    private JTextField viewBox;

    /**
     * JTextfField che mostra le coordinate di una cella.
     * @see TopPanel
     */
    private JTextField infoBox;

    /**
     * Salva il JTextField da modificare in caso di selezione di una cella e imposta  i comportamenti della JTable.
     *
     * @param myTableModel TableModel
     * @param viewBox      JTextField che mostra le informazioni di una cella
     * @param infoBox JTextField che mostra le coordinate
     */
    public MyJTable(MyTableModel myTableModel, JTextField viewBox, JTextField infoBox) {
        this.viewBox = viewBox;
        this.infoBox = infoBox;

        setRowHeight(22);
        setShowGrid(true);
        setFocusable(false);
        setModel(myTableModel);
        setCellSelectionEnabled(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        setSelectionBackground(new Color(0, 0, 0, 45));
    }

    /**
     * Modifica il testo che deve essere mostrato sul viewBox una volta selezionata una cella e mostra le
     * coordinate della cella selezionata.
     *
     * @param renderer {@link TableCellRenderer}
     * @param row      Indice di riga
     * @param col      Indice di colonna
     * @return cellRenderer
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Boolean isSelected = false;
        Boolean hasFocus = false;

        if (!isPaintingForPrint()) {
            isSelected = super.isCellSelected(row, col);
            boolean rowIsLead = (selectionModel.getLeadSelectionIndex() == row);
            boolean colIsLead = (columnModel.getSelectionModel().getLeadSelectionIndex() == col);
            hasFocus = (rowIsLead && colIsLead) && super.isFocusOwner();
        }

        Object value = super.getValueAt(row, col);
        JComponent cellRenderer = (JComponent) renderer.getTableCellRendererComponent(this, value, isSelected, hasFocus, row, col);

        if (isSelected) {
            infoBox.setText((char) ('A' + getSelectedColumn()) + Integer.toString(getSelectedRow() + 1));

            if (((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col) instanceof OperationCell)
                viewBox.setText(((OperationCell) ((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col)).getFormula());
            else
                viewBox.setText(value.toString());
        }

        return cellRenderer;
    }
}
