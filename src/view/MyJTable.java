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
     * Salva il JTextField da modificare in caso di selezione di una cella e imposta  i comportamenti della JTable.
     *
     * @param myTableModel TableModel
     * @param viewBox      JTextField che mostra le informazioni di una cella
     */
    public MyJTable(MyTableModel myTableModel, JTextField viewBox) {
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

    /**
     * Modifica il testo che deve essere mostrato sul viewBox una volta selezionata una cella.
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
            if (((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col) instanceof OperationCell)
                viewBox.setText(((OperationCell) ((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col)).getFormula());
            else
                viewBox.setText(value.toString());

            if (((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col) instanceof OperationCell)
                System.out.println("CELLA OPERAZIONE");
            else if (((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col) instanceof IntegerCell)
                System.out.println("CELLA INTERO");
            else if (((MyTableModel) this.getModel()).getData().getMatrix().get(row).get(col) instanceof StringCell)
                System.out.println("CELLA STRINGA");
            else
                System.out.println("CELLA GENERICA");
        }

        return cellRenderer;
    }
}
