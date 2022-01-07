package view.listener;

import controller.DataStructure;
import controller.FileOpener;
import controller.MyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonOpenListener implements ActionListener {
    private JTable table;
    private DataStructure data;
    private MyTableModel myTableModel;

    public ButtonOpenListener(JTable table, DataStructure data, MyTableModel myTableModel) {
        this.table = table;
        this.data = data;
        this.myTableModel = myTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FileOpener(this.table, this.data, this.myTableModel);
    }
}
