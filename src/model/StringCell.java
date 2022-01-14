package model;

import java.io.Serializable;

/**
 * Implementa il tipo cella stringa.
 * @author Mattia Lazzarini
 * @see model.GeneralCell
 * @see java.io.Serializable
 */
public class StringCell extends GeneralCell implements Serializable {
    /**
     * Stringa inserita.
     */
    private String string;

    /**
     * Salva la stringa inserita dall'utente nella JTable.
     * @param string
     */
    public StringCell(String string) {
        this.string = string;
    }

    /**
     * Ritorna la stringa memorizzata.
     * @return Stringa memorizzata
     */
    @Override
    public String toString() {
        return string;
    }
}
