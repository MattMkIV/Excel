package view.listener;

import controller.DataStructure;
import controller.FileOpener;
import controller.MyTableModel;
import view.MyJTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonOpenListener implements ActionListener {
    private MyJTable table;
    private DataStructure data;
    private MyTableModel myTableModel;

    public ButtonOpenListener(MyJTable table, DataStructure data, MyTableModel myTableModel) {
        this.table = table;
        this.data = data;
        this.myTableModel = myTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FileOpener(this.table, this.data, this.myTableModel);
    }
}
