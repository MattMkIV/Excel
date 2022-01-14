package model;

import java.io.Serializable;

/**
 * Implementa il tipo cella generico.
 * <p>Se una cella è appena stata inizializzata o viene lasciata vuota
 * dall'utente allora tale cella diventa di tipo generico.</p>
 * @author Mattia Lazzarini
 * @see java.io.Serializable
 */
public class GeneralCell implements Serializable {

    /**
     * Costruttore che inizializza la cella di tipo generico.
     */
    public GeneralCell() {}

    /**
     * Ritorna una stringa vuota in quanto se la cella è di questo tipo allora
     * sicuramente è vuota.
     * @return Stringa vuota
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * Ritorna zero se viene utilizzata questa cella all'interno di un'operaizone
     * @return Zero
     */
    public int getResult() {
        return 0;
    }
}
