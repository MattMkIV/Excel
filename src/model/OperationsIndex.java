package model;

import java.io.Serializable;


public class OperationsIndex  implements Serializable {
    private int rowIndex;
    private int colIndex;

    public OperationsIndex(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }
}
