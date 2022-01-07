package model;

import java.io.Serializable;

public class GeneralCell implements Serializable {
    private int row;
    private int col;

    public GeneralCell() {
        this.row = 0;
        this.col = 0;
    }

    public GeneralCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "";
    }

    public String getFormula() {
        return null;
    }

    public int getResult() {
        return 0;
    }
}
