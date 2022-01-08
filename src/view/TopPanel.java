package view;

import net.miginfocom.swing.MigLayout;
import view.listener.TextFieldViewListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

/**
 * Implementa i componenti del pannello superiore dell'interfaccia.
 * @author Mattia
 */
public class TopPanel extends JPanel {
    private final JTextField infoBox;
    private final JTextField viewBox;
    private final JButton buttonClear;
    private final JButton buttonSet;
    private final JButton saveAs;
    private final JButton open;
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

        buttonClear.setIcon(new NoScalingIcon(icon));
        buttonClear.setSelected(false);
        buttonClear.setBorderPainted(false);
        buttonClear.setToolTipText("Cancella contenuto cella");

        buttonSet = new JButton();
        buttonSet.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/V.png")));
        img = icon.getImage().getScaledInstance(buttonSet.getWidth(), buttonSet.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        buttonSet.setIcon(new NoScalingIcon(icon));
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

        saveAs.setIcon(new NoScalingIcon(icon));
        saveAs.setToolTipText("Salva");
        saveAs.setBorderPainted(false);
        saveAs.setSelected(false);

        open = new JButton();
        open.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Open.png")));
        img = icon.getImage().getScaledInstance(open.getWidth(), open.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        open.setIcon(new NoScalingIcon(icon));
        open.setToolTipText("Apri");
        open.setBorderPainted(false);
        open.setSelected(false);

        restore = new JButton();
        restore.setSize(new Dimension(21, 21));

        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Restore.png")));
        img = icon.getImage().getScaledInstance(restore.getWidth(), restore.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        restore.setIcon(new NoScalingIcon(icon));
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

    private class NoScalingIcon implements Icon {
        private Icon icon;

        public NoScalingIcon(Icon icon) {
            this.icon = icon;
        }

        public int getIconWidth() {
            return icon.getIconWidth();
        }

        public int getIconHeight() {
            return icon.getIconHeight();
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            AffineTransform at = g2d.getTransform();

            int scaleX = (int) (x * at.getScaleX());
            int scaleY = (int) (y * at.getScaleY());

            int offsetX = (int) (icon.getIconWidth() * (at.getScaleX() - 1) / 2);
            int offsetY = (int) (icon.getIconHeight() * (at.getScaleY() - 1) / 2);

            int locationX = scaleX + offsetX;
            int locationY = scaleY + offsetY;

            AffineTransform scaled = AffineTransform.getScaleInstance(1.0 / at.getScaleX(), 1.0 / at.getScaleY());
            at.concatenate(scaled);
            g2d.setTransform(at);

            icon.paintIcon(c, g2d, locationX, locationY);

            g2d.dispose();
        }
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
     * Ritorna il JTextField che mostra le coordinate della cella selezionata.
     * @return JTextField che mostra le coordinate
     */
    public JTextField getInfoBox() {
        return infoBox;
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
