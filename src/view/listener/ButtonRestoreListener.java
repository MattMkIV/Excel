package view.listener;

import controller.DataStructure;
import controller.FileOpener;
import controller.MyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ButtonRestoreListener implements ActionListener {
    private JTable table;
    private DataStructure data;
    private MyTableModel myTableModel;

    public ButtonRestoreListener(JTable table, DataStructure data, MyTableModel myTableModel) {
        this.table = table;
        this.data = data;
        this.myTableModel = myTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FileOpener(table, data, myTableModel, new File(System.getProperty("java.io.tmpdir") + File.separator + "ProjectTmp.ex"));
    }
}
