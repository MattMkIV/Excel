package model;

import java.io.Serializable;

/**
 * Implementa il tipo cella intero.
 *
 * @author Mattia Lazzarini
 * @see model.GeneralCell
 * @see java.io.Serializable
 */
public class IntegerCell extends GeneralCell implements Serializable {
    /**
     * Numero inserito.
     */
    private int number;

    /**
     * Memorizza il tipo intero inserito dall'utente nella JTable.
     *
     * @param number Insero inserito
     */
    public IntegerCell(int number) {
        this.number = number;
    }

    /**
     * Ritorna il numero memorizzato.
     *
     * @return Numero memorizzato
     */
    @Override
    public int getResult() {
        return number;
    }

    /**
     * Ritorna il numero memorizzato come tipo stringa.
     *
     * @return Numero memorizzato di tipo stringa
     */
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
