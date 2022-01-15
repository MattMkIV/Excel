package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Implementa i componenti del pannello superiore dell'interfaccia.
 * @author Mattia Lazzarini
 * @see JPanel
 */
public class TopPanel extends JPanel {
    /**
     * Mostra le coordinate della cella selezionata.
     */
    private final JTextField infoBox;
    /**
     * Mostra le formula e può essere modificato.
     */
    private final JTextField viewBox;
    /**
     * Bottone per il reset del contenuto di una cella.
     */
    private final JButton buttonClear;
    /**
     * Bottone per confermare il contenuto della viewBox.
     */
    private final JButton buttonSet;
    /**
     * Bottone per il salvataggio manuale.
     */
    private final JButton saveAs;
    /**
     * Bottone per aprire un file.
     */
    private final JButton open;
    /**
     * Bottone per ripristinare il file temporaneo.
     */
    private final JButton restore;

    /**
     * Inizializza tutti gli elementi del pannello superiore del programma.
     * <p>Imposta il layout di questo pannello utilizzando una libreria esterna che permette di adattare
     * gli elementi al suo interno in base alla dimensione (anche dinamica) del JFrame in cui è inserito.
     * Inoltre ad ogni elemento aggiunge un ToolTip con una breve descrizione della funzione di quell'elemento,
     * imposta le icone degli elementi e imposta i parametri di comportamento</p>
     */
    public TopPanel() {
        setLayout(new MigLayout("fillx"));

        infoBox = new JTextField();
        infoBox.setPreferredSize(new Dimension(5, 26));
        infoBox.setToolTipText("Coordinate");
        infoBox.setEnabled(false);

        buttonClear = new JButton();
        buttonClear.setSize(new Dimension(21, 21));

        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/X.png")));
        Image img = icon.getImage().getScaledInstance(buttonClear.getWidth(), buttonClear.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        buttonClear.setIcon(icon);
        buttonClear.setSelected(false);
        buttonClear.setBorderPainted(false);
        buttonClear.setToolTipText("Cancella contenuto cella");

        buttonSet = new JButton();
        buttonSet.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/V.png")));
        img = icon.getImage().getScaledInstance(buttonSet.getWidth(), buttonSet.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        buttonSet.setIcon(icon);
        buttonSet.setToolTipText("Imposta valore cella");
        buttonSet.setBorderPainted(false);

        viewBox = new JTextField(100);
        viewBox.setPreferredSize(new Dimension(100, 26));
        viewBox.setToolTipText("Inserire o  modificare il testo di una cella selezionata");

        saveAs = new JButton();
        saveAs.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SaveAs.png")));
        img = icon.getImage().getScaledInstance(saveAs.getWidth(), saveAs.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        saveAs.setIcon(icon);
        saveAs.setToolTipText("Salva");
        saveAs.setBorderPainted(false);
        saveAs.setSelected(false);

        open = new JButton();
        open.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Open.png")));
        img = icon.getImage().getScaledInstance(open.getWidth(), open.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        open.setIcon(icon);
        open.setToolTipText("Apri");
        open.setBorderPainted(false);
        open.setSelected(false);

        restore = new JButton();
        restore.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Restore.png")));
        img = icon.getImage().getScaledInstance(restore.getWidth(), restore.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        restore.setIcon(icon);
        restore.setToolTipText("Ripristina il foglio perso");
        restore.setBorderPainted(false);
        restore.setSelected(false);


        add(infoBox);
        add(buttonClear);
        add(buttonSet);
        add(viewBox, "pushx, growx");
        add(saveAs);
        add(open);
        add(restore);
    }

    /**
     * Ritorna il bottone che permette l'apertura di un file.
     * @return JButton per l'apertura dei file
     */
    public JButton getOpen() {
        return open;
    }

    /**
     * Ritorna il bottone che permette di salvare.
     * @return JButton per il salvataggio
     */
    public JButton getSaveAs() {
        return saveAs;
    }

    /**
     * Ritorna il bottone di ripristino del file temporaneo.
     * @return JButton per il ripristino
     */
    public JButton getRestore() {
        return restore;
    }

    /**
     * RItorna il JTextField che permette la modifica delle celle.
     * @return JTextField che permette la modifica delle celle
     */
    public JTextField getViewBox() {
        return viewBox;
    }

    /**
     * Ritorna il JButton che conferma il contenuto inserito nel ViewBox.
     * @return JButton che conferma il contenuto inserito nel ViewBox
     */
    public JButton getButtonSet() {
        return buttonSet;
    }

    /**
     * Ritorna il bottone che resetta il contenuto della cella selezionata.
     * @return JButton che resetta il contenuto della cella selezionata
     */
    public JButton getButtonClear() {
        return buttonClear;
    }
}
