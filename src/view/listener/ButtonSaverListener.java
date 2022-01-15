package view.listener;

import controller.DataStructure;
import controller.FileSaver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementa l'evento del salvataggio manuale.
 *
 * @author Mattia Lazzarini
 * @see ActionListener
 */
public class ButtonSaverListener implements ActionListener {
    /**
     * Struttura dati {@link DataStructure}.
     */
    private DataStructure data;

    /**
     * Salva la struttura dati {@link DataStructure}.
     *
     * @param data Struttura dati
     */
    public ButtonSaverListener(DataStructure data) {
        this.data = data;
    }

    /**
     * Crea un oggetto {@link FileSaver}.
     *
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new FileSaver(data);
    }
}
