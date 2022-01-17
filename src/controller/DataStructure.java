package controller;

import model.GeneralCell;
import model.OperationsIndex;
import model.StringCell;

import java.io.Serializable;
import java.util.ArrayList;

import static java.util.regex.Pattern.matches;

/**
 * Struttura dati.
 * <p>Classe che inizializza la struttura dati e implementa i medoti per il controllo dei valori inseriti nella JTable
 * e di assegnazione dei vari tipi delle celle utilizzando il polimorfismo</p>
 *
 * @author Mattia Lazzarini
 * @see java.io.Serializable
 */
public class DataStructure implements Serializable {
    /**
     * Tipo di operazione.
     */
    private char op;
    /**
     * Valori dell'operazione.
     */
    private int[] opComponent;
    /**
     * Numero di righe.
     */
    private final int row = 100;
    /**
     * Numero di colonne.
     */
    private final int col = 26;
    /**
     * Struttura dati secondaria per la memorizzazione delle posizioni delle celle di
     * tipo operazione.
     *
     * @see ArrayList
     */
    private final ArrayList<OperationsIndex> indices;
    /**
     * Struttura dati principale del foglio di calcolo.
     *
     * @see ArrayList
     */
    private ArrayList<ArrayList<GeneralCell>> matrix;

    /**
     * Inizializza la struttura dati principale ad un numero fissato numero di righe e colonne e quella
     * per la memorizzazione degli indici di tutte le formule(Default: 5).
     */
    public DataStructure() {
        matrix = new ArrayList<>(row);

        for (int i = 0; i < row; i++) {
            matrix.add(new ArrayList<>(col));
            for (int j = 0; j < col; j++)
                matrix.get(i).add(new GeneralCell());
        }

        indices = new ArrayList<>(5);
    }

    /**
     * Data una stringa verifica il suo contenuto.
     * <p>Valori di ritorno:</p>
     * <ul><li>0 se la stringa è un intero</li>
     * <li>1 se il contenuto combacia con la regex e quindi se è stata inserita una formula</li>
     * <li>2 se la cella è vuota (quindi dovrà essere di tipo generico)</li>
     * <li>3 se il contenuto della cella è una stringa</li></ul>
     *
     * @param cellContent Contenuto della cella modificata
     * @return Intero che rappresenta il tipo che la cella dovrà assumere
     */
    public int checkTypeCell(String cellContent) {
        try {
            Integer.parseInt(cellContent);
            return 0;

        } catch (NumberFormatException e) {

            String pattern = "=[A-Z][0-9]{1,3}?[+|\\-|*|/][A-Z][0-9]{1,3}?";
            String pattern2 = "=[0-9]+?[+|\\-|*|/][0-9]+?";
            if (matches(pattern, cellContent))
                return 1;
            else if (matches(pattern2, cellContent))
                return 2;
            else if (!cellContent.equals(""))
                return 3;
        }
        return 4;
    }

    /**
     * Vengono estratti dalla formula le informazioni relative agli indici degli operandi.
     * <p>Le informazioni estratte, in particolare i riferimenti alle celle a cui fa riferimento la formula, vengono
     * salvati all'interno di un array di interi opCompnent, mentre il tipo di operazione da compiere sugli
     * operandi viene salvato all'interno del char op</p>
     *
     * @param cellContent Contenuto della cella di tipo formula
     */
    public void extractOperand(String cellContent) {
        opComponent = new int[4];
        boolean firstPart = true;
        StringBuilder buffer = new StringBuilder();
        StringBuilder buffer2 = new StringBuilder();

        opComponent[0] = cellContent.charAt(1) - 'A';

        int i = 2;
        while (i < cellContent.length()) {
            if (cellContent.charAt(i) == '+' || cellContent.charAt(i) == '-' || cellContent.charAt(i) == '*' || cellContent.charAt(i) == '/') {
                op = cellContent.charAt(i);
                opComponent[2] = cellContent.charAt(++i) - 'A';
                firstPart = false;
            } else {
                if (firstPart)
                    buffer.append(cellContent.charAt(i));
                else
                    buffer2.append(cellContent.charAt(i));
            }
            i++;
        }
        opComponent[1] = Integer.parseInt(buffer.toString()) - 1;
        opComponent[3] = Integer.parseInt(buffer2.toString()) - 1;
    }

    /**
     * Vengono estratti dalla formula con interi le informazioni relative al valore degli operandi.
     * <p>Le informazioni estratte vengono salvati all'interno di un array di interi opCompnent, mentre il tipo di
     * operazione da compiere sugli operandi viene salvato all'interno del char op.</p>
     *
     * @param cellContent Contenuto della cella di tipo formula
     */
    public void takeOperand(String cellContent) {
        opComponent = new int[2];
        StringBuilder buffer = new StringBuilder(cellContent);

        int position;
        if (cellContent.contains("+"))
            position = cellContent.indexOf("+");
        else if (cellContent.contains("-"))
            position = cellContent.indexOf("-");
        else if (cellContent.contains("*"))
            position = cellContent.indexOf("*");
        else
            position = cellContent.indexOf("/");

        op = buffer.charAt(position);
        opComponent[0] = Integer.parseInt(buffer.substring(1, position));
        opComponent[1] = Integer.parseInt(buffer.substring(position + 1, buffer.length()));
    }

    /**
     * Verifica sul tipo di operandi inseriti nella formula.
     * <p>Viene ritornato False se almeno uno dei due operandi fa riferimento ad un oggetto di tipo String
     * altrimenti viene ritornato True.</p>
     *
     * @return True se l'oprazione può essere effettuata
     */
    public boolean checkCorrectOperand() {
        return !(matrix.get(opComponent[1]).get(opComponent[0]) instanceof StringCell) && !(matrix.get(opComponent[3]).get(opComponent[2]) instanceof StringCell);
    }

    /**
     * Viene aggiornata la struttura dati che tiene traccia della posizione delle operazioni aggiungendo un nuovo elemento.
     *
     * @param rowIndex Indice di riga della cella operazione
     * @param colIndex Indice di colonna della cella operazione
     */
    public void addIndex(int rowIndex, int colIndex) {
        indices.add(new OperationsIndex(rowIndex, colIndex));
    }

    /**
     * Viene aggiornata la struttura dati che tiene traccia della posizione delle operazioni rimuovendo un elemento.
     *
     * @param row Indice di riga della cella operazione da rimuovere
     * @param col Indice di colonna della cella operazione da rimuovere
     */
    public void removeIndex(int row, int col) {
        for (int i = 0; i < indices.size(); i++)
            if ((indices.get(i).getRowIndex() == row) && indices.get(i).getColIndex() == col)
                indices.remove(i);
    }

    /**
     * Ritorna la struttura dati contenente gli indici delle operazioni.
     *
     * @return Struttura dati contenente gli indici delle operazioni
     */
    public ArrayList<OperationsIndex> getIndices() {
        return indices;
    }

    /**
     * Ritorna la matrice contenente tutti i valori inseriti e le relative informazioni.
     *
     * @return Matrice contenente tutti i valori inseriti e le relative informazioni
     */
    public ArrayList<ArrayList<GeneralCell>> getMatrix() {
        return matrix;
    }

    /**
     * Ritorna l'array contenente i componenti dell'operazione immessa dall'utente.
     *
     * @return Array di interi
     */
    public int[] getOpComponent() {
        return opComponent;
    }

    /**
     * Ritorna il tipo di operazione.
     *
     * @return Tipo di operazione
     */
    public char getOp() {
        return op;
    }

    /**
     * Ritorna il numero di righe della JTable e della matrice.
     *
     * @return Numero di righe della JTable e della matrice
     */
    public int getRow() {
        return row;
    }

    /**
     * Ritorna il numero di colonne della JTable e della matrice.
     *
     * @return Numero di colonne della JTable e della matrice
     */
    public int getCol() {
        return col;
    }

    /**
     * Assegna la variabile matrix al parametro passato
     *
     * @param matrix Struttura dati matrice
     */
    public void setMatrix(ArrayList<ArrayList<GeneralCell>> matrix) {
        this.matrix = matrix;
    }
}
