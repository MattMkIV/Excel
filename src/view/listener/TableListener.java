package view.listener;

import controller.DataStructure;
import controller.FileSaver;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TableListener implements TableModelListener {
    private DataStructure data;
    private boolean firstMod = false;

    public TableListener(DataStructure data) {
        this.data = data;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (!firstMod) {
            Runnable runnable = () -> new FileSaver(data, new File(System.getProperty("java.io.tmpdir") + File.separator + "ProjectTmp"));

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);

            firstMod = true;
        }
    }
}
