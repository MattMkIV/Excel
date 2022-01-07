package view.listener;

import controller.DataStructure;
import controller.FileSaver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSaverListener implements ActionListener {
    private DataStructure data;

    public ButtonSaverListener(DataStructure data) {
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FileSaver(data);
    }
}
