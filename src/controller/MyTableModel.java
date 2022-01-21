package controller;

import model.StringCell;
import model.GeneralCell;
import model.IntegerCell;
import model.OperationCell;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * Reimplementazione della TableModel per gestire i vari tipi di dati inseriti.
 *
 * @author Mattia Lazzarini
 * @see DefaultTableModel
 */
public class MyTableModel extends DefaultTableModel {
    /**
     * Numero di righe.
     */
    private int row;
    /**
     * Numero di colonne.
     */
    private int col;
    /**
     * Struttura dati {@link DataStructure}
     */
    private DataStructure data;
    /**
     * Indici di riga.
     */
    private final Vector<Integer> rowHeader;

    /**
     * Inizializza i parametri di default della TableModel.
     * <p>Vengono impostati i valori relativi al numero di righe e colonne della JTable, impostata la grafica degli indici di riga,
     * viene salvata una copia per riferimento della struttura dati e popolata la tabella in base ai valori della matrice in "data".</p>
     *
     * @param data Parametro contenente la struttura dati
     */
    public MyTableModel(DataStructure data) {
        super.setRowCount(row);
        super.setColumnCount(col);

        this.data = data;
        this.row = data.getRow();
        this.col = data.getCol();
        this.rowHeader = new Vector<>();
        this.populateTable();
    }

    /**
     * Popola il gli elementi della TableModel.
     * <p>Setta tutti gli indici da visualizzare come indici di riga e in base al contenuto della struttura dati
     * principale contenuta all'interno di data modifica la TableModel.</p>
     */
    private void populateTable() {
        for (int i = 0; i < this.row; i++)
            this.rowHeader.addElement(i + 1);

        GeneralCell[][] buffer = new GeneralCell[data.getRow()][data.getCol()];

        for (int i = 0; i < data.getRow(); i++)
            for (int j = 0; j < data.getCol(); j++)
                buffer[i][j] = data.getMatrix().get(i).get(j);

        setDataVector(buffer, null);
    }

    /**
     * Imposta il valore nella matrice in base al tipo di dato inserito nella JTable.
     * <p>Se è stata inserita una formula che ha come operandi delle stringhe stampa nella cella un messaggio di
     * errore seguito dalla formula che causa l'errore.</p>
     *
     * @param value Valore inserito nella cella
     * @param row   Indice di riga della cella modificata
     * @param col   Indice di colonna della cella modificata
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        int cellType = data.checkTypeCell((String) value);

        if (cellType == 0 || cellType == 3 || cellType == 4)
            if (data.getMatrix().get(row).get(col) instanceof OperationCell)
                data.removeIndex(row, col);

        switch (cellType) {
            case 0: {
                data.getMatrix().get(row).set(col, new IntegerCell(Integer.parseInt((String) value)));
                break;
            }

            case 1: {
                data.extractOperand((String) value);

                if (data.checkCorrectOperand()) {
                    setOperationCell(value, row, col);
                    super.setValueAt(data.getMatrix().get(row).get(col).getResult(), row, col);
                    fireTableDataChanged(false, row, col);
                } else
                    setValueAt("Errore: " + value, row, col);
                break;
            }
            case 2: {
                data.takeOperand((String) value);

                data.getMatrix().get(row).set(col, new OperationCell((String) value, data.getOp(), data.getOpComponent()[0], data.getOpComponent()[1]));
                super.setValueAt(data.getMatrix().get(row).get(col).getResult(), row, col);
                break;
            }
            case 3: {
                data.getMatrix().get(row).set(col, new StringCell((String) value));
                break;
            }
            case 4: {
                data.getMatrix().get(row).set(col, new GeneralCell());
                break;
            }
        }
        if (cellType != 1 && cellType != 2)
            super.setValueAt(value, row, col);

        if (cellType != 1)
            fireTableDataChanged(true, row, col);
    }

    /**
     * Imposta nella matrice il tipo di cella Operazione.
     * <p>Setta la matrice nella posizione di riga e colonna come tipo Operazione con riferimenti ad altre celle
     * e se la cella non lo era già allora viene aggiornata la lista con i riferimenti alle celle Operazione.</p>
     *
     * @param value Valore inserito nella cella
     * @param row   Indice di riga della cella modificata
     * @param col   Indice di colonna della cella modificata
     */
    private void setOperationCell(Object value, int row, int col) {
        int firstOpValue = data.getMatrix().get(data.getOpComponent()[1]).get(data.getOpComponent()[0]).getResult();
        int secondOpValue = data.getMatrix().get(data.getOpComponent()[3]).get(data.getOpComponent()[2]).getResult();

        boolean state = !(data.getMatrix().get(row).get(col) instanceof OperationCell);     //mi serve altrimenti mi aggiunge elementi all'arraylist all'infinito

        data.getMatrix().get(row).set(col, new OperationCell((String) value, data.getOp(), firstOpValue, secondOpValue));

        if (state)
            data.addIndex(row, col);
    }

    /**
     * Aggiorna la struttura dati e il contenuto delle celle a seguito del cambiamento di un valore.
     * <p>Se:</p>
     * <ul><li>Il valore inserito è diverso da una formula che ha come operandi altre celle verranno aggiornate
     * tutte le formule</li>
     * <li>Il valore inserito è una formula che ha come operandi altre celle allora vengono aggiornate solo le formule
     * inserite dopo quest'ultima</li></ul>
     * Viene stampato un messaggio di errore se una formula ha come operandi delle celle di tipo stringa.
     * @param all Se true aggiorna tutte le formule, altrimenti solo da quella modificata in poi
     * @param row Indice di riga modificata
     * @param col Indice di colonna modificata
     */
    public void fireTableDataChanged(boolean all, int row, int col) {
        if (!all) {
            int pos = 0;
            for (int i = 0; i < data.getIndices().size(); i++)
                if (data.getIndices().get(i).getRowIndex() == row && data.getIndices().get(i).getColIndex() == col)
                    pos = i;

            for (int i = pos + 1; i < data.getIndices().size(); i++) {
                String cellContent = ((OperationCell) data.getMatrix().get(data.getIndices().get(i).getRowIndex()).get(data.getIndices().get(i).getColIndex())).getFormula();

                setValueAt(cellContent, data.getIndices().get(i).getRowIndex(), data.getIndices().get(i).getColIndex());
            }
        } else {
            for (int i = 0; i < data.getIndices().size(); i++) {
                String cellContent = ((OperationCell) data.getMatrix().get(data.getIndices().get(i).getRowIndex()).get(data.getIndices().get(i).getColIndex())).getFormula();

                data.extractOperand(cellContent);
                if(data.checkCorrectOperand())
                    setValueAt(cellContent, data.getIndices().get(i).getRowIndex(), data.getIndices().get(i).getColIndex());
                else
                    setValueAt("Errore: " + ((OperationCell) data.getMatrix().get(data.getIndices().get(i).getRowIndex()).get(data.getIndices().get(i).getColIndex())).getFormula(), data.getIndices().get(i).getRowIndex(), data.getIndices().get(i).getColIndex());
            }
        }
    }

    /**
     * Ritorna il Vector che contiene gli indici di riga
     *
     * @return Indici di riga
     */
    public Vector<Integer> getVector() {
        return rowHeader;
    }

    /**
     * Ritorna la classe contente la struttura dati.
     *
     * @return DataStructure
     */
    public DataStructure getData() {
        return data;
    }

    /**
     * Ritorna il numero di righe della JTable.
     *
     * @return Numero di righe
     */
    @Override
    public int getRowCount() {
        return row;
    }

    /**
     * Ritorna il numero di colonne della JTable.
     *
     * @return Numero di colonne
     */
    @Override
    public int getColumnCount() {
        return col;
    }
}
