package view.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClearListener implements ActionListener {
    private JTable table;

    public ButtonClearListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rowSelected = table.getSelectedRow();
        int colSelected = table.getSelectedColumn();

        if (!(rowSelected < 0 && colSelected < 0))
            table.setValueAt("", table.getSelectedRow(), table.getSelectedColumn());
    }
}
