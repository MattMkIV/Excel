package model;

import java.io.Serializable;

/**
 * Gestisce gli indici delle celle di tipo operazione.
 * <p>Memorizza gli indici di una cella di tipo operazione.</p>
 * @author Mattia Lazzarini
 * @see java.io.Serializable
 */
public class OperationsIndex  implements Serializable {
    /**
     * Indice di riga.
     */
    private int rowIndex;
    /**
     * Indice di colonna.
     */
    private int colIndex;

    /**
     * Salva gli indici di riga e di colonna della cella di tipo operazione.
     * @param rowIndex Indice di riga
     * @param colIndex Indice di colonna
     */
    public OperationsIndex(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    /**
     * Ritorna l'indice di riga.
     * @return Indice di riga
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Ritorna l'indice di colonna.
     * @return Indice di colonna
     */
    public int getColIndex() {
        return colIndex;
    }
}
