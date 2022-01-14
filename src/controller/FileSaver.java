package controller;

import javax.swing.*;
import java.io.*;

/**
 * Salvataggio del foglio elettronico.
 * <p>Assegna la logica al bottone per il salvataggio manuale e gestisce il salvataggio automatico</p>
 * @author Mattia Lazzarini
 * @see javax.swing.JFileChooser
 */
public class FileSaver extends JFileChooser {
    /**
     * Percorso in cui salvare il file.
     */
    private File file;
    /**
     * {@link DataStructure}
     */
    private DataStructure data;

    /**
     * Costruttore per il salvataggio manuale.
     * <p>Mostra un JFileChooser per permettere all'utente di selezionare il percorso in cui salvare il file e se il nome
     * del file inserito dall'utente esiste già in quella cartella viene chiesto se si vuole sovrascrivere, inoltre
     * aggiunge l'estensione .ex al nome del file.</p>
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
     * <p>Salva automaticamente il foglio elettronico nella cartella temp del sistema operativo ogni 10 secondi.</p>
     * @param data Classe con la struttura dati
     * @param file Percorso di salvataggio automatico
     */
    public FileSaver(DataStructure data, File file) {
        this.data = data;
        this.file = file;

        Saver();
    }

    /**
     * Metodo che gestisce il salvataggio.
     * <p>In base al percorso salvato nell'oggetto "file" viene salvato un file binario contenente la struttura dati
     * principale {@link DataStructure}</p>
     * @see FileOutputStream
     * @see ObjectOutputStream
     */
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
