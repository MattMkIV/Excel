package controller;

import javax.swing.*;
import java.io.*;

/**
 * Salvataggio del foglio elettronico.
 */
public class FileSaver extends JFileChooser {
    private File file;
    private DataStructure data;

    /**
     * Costruttore per il salvataggio manuale.
     * Mostra un JFileChooser per permettere all'utente di selezionare il percorso in cui salvare il file e se nome
     * del file inserito dall'utente esiste già in quella cartella viene chiesto se si vuole sovrascrivere, inoltre
     * aggiunge l'estensione .ex al nome del file
     * @param data Classe con la struttura dati
     */
    public FileSaver(DataStructure data) {
        this.data = data;

        setDialogTitle("Selezionare il percorso di salvataggio");
        setApproveButtonText("Save");

        int choice = showOpenDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            file = getSelectedFile();

            if(!file.getPath().endsWith(".ex"))
                 file = new File(file.getPath() + ".ex");

            if (file.exists()) {
                int result = JOptionPane.showConfirmDialog(null, "Il file in questa cartella esiste già, sovrascrivere?", "File già esistente", JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION)
                    Saver();

            } else
                Saver();
        }
    }

    /**
     * Costruttore per il salvataggio automatico.
     * Salva automaticamente il foglio elettronico nella cartella temp del sistema operativo ogni 2 secondi
     * @param data Classe con la struttura dati
     * @param file Percorso di salvataggio automatico
     */
    public FileSaver(DataStructure data, File file) {
        this.data = data;
        this.file = file;

        Saver();
        System.out.println("file TEMPORANEO salvato");
    }

    private void Saver() {
        try {
            FileOutputStream fos = new FileOutputStream(this.file);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(data.getMatrix());

            out.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
