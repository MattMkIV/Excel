package view.listener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldViewListener implements KeyListener {
    private JTable table;
    private JTextField viewBox;

    public TextFieldViewListener(JTable table, JTextField viewBox) {
        this.table = table;
        this.viewBox = viewBox;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == '\n') {
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
