package view;

import com.formdev.flatlaf.FlatDarkLaf;
import view.listener.*;

import javax.swing.*;
import java.awt.*;

/**
 * Gestisce il {@link JFrame}, i suoi pannelli e i listeners dei componenti.
 *
 * @author Mattia Lazzarini
 */
public class FrameClass extends JFrame {

    /**
     * Vengono gestiti i componenti del frame e impostati i listeners.
     * <p>Setta il tema FlatDarkLaf, inizializza i due pannelli del frame, aggiunge i listeners dei componenti e imposta
     * il comportamento del JFrame</p>
     */
    public FrameClass() {
        FlatDarkLaf.setup();
        UIManager.put("Button.arc", 13);
        UIManager.put("TextComponent.arc", 18);

        setTitle("Foglio elettronico");

        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel();
        TableSettings tableSettings = new TableSettings(topPanel.getViewBox());

        //topPanel listeners
        topPanel.getSaveAs().addActionListener(new ButtonSaverListener(tableSettings.getData()));
        topPanel.getButtonClear().addActionListener(new ButtonClearListener(tableSettings.getTable()));
        topPanel.getButtonSet().addActionListener(new ButtonSetListener(topPanel, tableSettings.getTable()));
        topPanel.getOpen().addActionListener(new ButtonOpenListener(tableSettings.getTable(), tableSettings.getData(), tableSettings.getMyTableModel()));
        topPanel.getRestore().addActionListener(new ButtonRestoreListener(tableSettings.getTable(), tableSettings.getData(), tableSettings.getMyTableModel()));
        topPanel.getViewBox().addKeyListener(new TextFieldViewListener(tableSettings.getTable(), topPanel.getViewBox()));

        //tableSettings listeners
        tableSettings.getMyTableModel().addTableModelListener(new TableListener(tableSettings.getData()));
        tableSettings.getTable().setDefaultEditor(Object.class, CellEditor.make(tableSettings.getData(), topPanel.getViewBox()));

        add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollable = new JScrollPane(tableSettings.getTable());
        scrollable.setRowHeaderView(new RowHeader(tableSettings.getMyTableModel(), tableSettings.getTable()).getRowHeader());
        scrollable.setColumnHeaderView(tableSettings.getTable().getTableHeader());
        scrollable.getHorizontalScrollBar().setUnitIncrement(20);
        scrollable.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollable, BorderLayout.CENTER);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((screenSize.width / 2) + 600, (screenSize.height / 2) + 450));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMaximumSize(screenSize);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
