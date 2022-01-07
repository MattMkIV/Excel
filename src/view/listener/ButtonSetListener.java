package view.listener;

import view.TopPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSetListener implements ActionListener {
    private JTable table;
    private TopPanel panelTop;

    public ButtonSetListener(TopPanel panelTop, JTable table) {
        this.table = table;
        this.panelTop = panelTop;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!table.isEditing()) {
            int rowSelected = table.getSelectedRow();
            int colSelected = table.getSelectedColumn();

            if (!(rowSelected < 0 && colSelected < 0)) {
                String buffer = panelTop.getViewBox().getText();

                table.setValueAt(buffer, rowSelected, colSelected);
            }
        }
    }
}
