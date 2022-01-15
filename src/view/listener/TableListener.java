package view.listener;

import controller.DataStructure;
import controller.FileSaver;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementa il listener della MyJTable.
 *
 * @author Mattia Lazzarini
 * @see TableListener
 */
public class TableListener implements TableModelListener {
    /**
     * Struttura dati {@link DataStructure}.
     */
    private DataStructure data;
    /**
     * Prima modifica effettuata alla JTable.
     */
    private boolean firstMod = false;

    /**
     * Salva la struttura dati {@link DataStructure}.
     *
     * @param data Struttura dati {@link DataStructure}
     */
    public TableListener(DataStructure data) {
        this.data = data;
    }

    /**
     * Avvia il salvataggio automatico quando viene effettuata la prima modifica alla JTable.
     *
     * @param e {@link TableModelEvent}
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        if (!firstMod) {
            Runnable runnable = () -> {
                new FileSaver(data, new File(System.getProperty("java.io.tmpdir") + File.separator + "ProjectTmp.ex"));
            };

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);

            firstMod = true;
        }
    }
}
