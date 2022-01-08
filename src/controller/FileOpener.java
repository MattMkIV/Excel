package controller;

import model.GeneralCell;
import model.IntegerCell;
import model.OperationCell;
import model.OperationsIndex;
import view.listener.TableListener;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Implementazione dell'apertura di un file salvato.
 * <p>Classe che assegna la logica al bottone che permette l'apertura di un file salvato</p>
 */
public class FileOpener extends JFileChooser {
    private File file;
    private DataStructure data;
    private JTable table;
    private MyTableModel myTableModel;

    /**
     * Costrutture per l'apertura di un file scelto dall'utente.
     * <p>Permette di aprire un file scelto dall'utente tramite un JFileChooser, se il file selzionato non termina con
     * l'estensione ".ex" viene mostrato un errore di incompatibilità</p>
     * @param table JTable su cui mostrare i dati del file
     * @param data Struttura su cui memorizzare i dati del file aperto
     * @param myTableModel TableModel per aggiornare le strutture dati
     */
    public FileOpener(JTable table, DataStructure data, MyTableModel myTableModel) {
        this.table = table;
        this.data = data;
        this.myTableModel = myTableModel;

        setDialogTitle("Selezionare il file da aprire");
        setApproveButtonText("Open");

        int choice = showOpenDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            file = getSelectedFile();

            if(!file.getPath().endsWith(".ex"))
                JOptionPane.showMessageDialog(null, "È stato selezionato tipo di file non compatibile", "Formato errato!", JOptionPane.ERROR_MESSAGE);
            else
                Opener();
        }
    }

    /**
     * Costruttore per l'apertura del file temporaneo.
     * <p>Permette di aprire il file temporaneo presente nella cartella temp del sistema operativo
     * se il file non esiste allora sarà mostrato un avviso all'utente</p>
     * @param table JTable su cui mostrare i dati del file
     * @param data Struttura su cui memorizzare i dati del file aperto
     * @param myTableModel TableModel per aggiornare le strutture dati
     * @param file Percorso della cartella temp in base al sistema operativo
     */
    public FileOpener(JTable table, DataStructure data,  MyTableModel myTableModel, File file) {
        this.table = table;
        this.data = data;
        this.file = file;
        this.myTableModel = myTableModel;

        if (file.exists()) {
            int result = JOptionPane.showConfirmDialog(null, "I dati inseriti nel foglio corrente andranno persi, sovrascrivere?", "Apertura file temporaneo", JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.YES_OPTION)
                Opener();

        } else
            JOptionPane.showMessageDialog(null, "Nessun file temporaneo trovato", "Errore!", JOptionPane.ERROR_MESSAGE);
    }

    private void Opener() {
        try {
            FileInputStream fileIn = new FileInputStream(this.file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            data.setMatrix((ArrayList<ArrayList<GeneralCell>>) in.readObject());

            for(int i=0; i<table.getRowCount(); i++) {
                for(int j=0; j<table.getColumnCount(); j++) {
                    if(data.getMatrix().get(i).get(j) instanceof OperationCell) {
                        myTableModel.setValueAt(data.getMatrix().get(i).get(j).getFormula(), i, j);
                        data.addIndex(i, j);        //la cella è già di tipo Operazione quindi il "setValueAt" non aggiunge questi indici
                    } else
                        table.setValueAt(data.getMatrix().get(i).get(j).toString(), i, j);
                }
            }

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
