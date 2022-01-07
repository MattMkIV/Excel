package view;

import com.formdev.flatlaf.FlatDarkLaf;
import view.listener.*;

import javax.swing.*;
import java.awt.*;

/**
 * Gestisce i componenti dei pannelli.
 */
public class FrameClass extends JFrame {

    /**
     * Vengono gestiti i componenti del frame e impostati i listeners.
     * <p>Setta il tema FlatDarkLaf, inizializza i due pannelli del frame, aggiunge i listeners dei componenti e imposta
     * il comportamento del JFrame</p>
     */
    public FrameClass() {
        FlatDarkLaf.setup();
        UIManager.put("Button.arc", 14);
        UIManager.put("TextComponent.arc", 19);

        setTitle("Foglio elettronico");

        setLayout(new BorderLayout());

        TopPanel topPanel = new TopPanel();
        TableSettings tableSettings = new TableSettings();

        //topPanel listeners
        topPanel.getSaveAs().addActionListener(new ButtonSaverListener(tableSettings.getData()));
        topPanel.getButtonClear().addActionListener(new ButtonClearListener(tableSettings.getTable()));
        topPanel.getButtonSet().addActionListener(new ButtonSetListener(topPanel, tableSettings.getTable()));
        topPanel.getOpen().addActionListener(new ButtonOpenListener(tableSettings.getTable(), tableSettings.getData(), tableSettings.getMyTableModel()));
        topPanel.getRestore().addActionListener(new ButtonRestoreListener(tableSettings.getTable(), tableSettings.getData(), tableSettings.getMyTableModel()));

        //tableSettings listeners
        tableSettings.getTable().addMouseListener(new CellMouseListener(tableSettings.getTable(), tableSettings.getData(), topPanel));
        tableSettings.getMyTableModel().addTableModelListener(new TableListener(tableSettings.getData()));
        tableSettings.getTable().setDefaultEditor(Object.class, CellEditor.make(tableSettings.getData()));

        add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollable = new JScrollPane(tableSettings.getTable());
        scrollable.setRowHeaderView(new RowHeader(tableSettings.getMyTableModel(), tableSettings.getTable()).getRowHeader());
        scrollable.setColumnHeaderView(tableSettings.getTable().getTableHeader());
        scrollable.getHorizontalScrollBar().setUnitIncrement(20);
        scrollable.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollable, BorderLayout.CENTER);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((screenSize.width / 2) + 400,(screenSize.height / 2) + 350));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMaximumSize(screenSize);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
