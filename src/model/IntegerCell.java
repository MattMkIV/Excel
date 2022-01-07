package model;

import java.io.Serializable;

public class IntegerCell extends GeneralCell implements Serializable {
    private int number;

    public IntegerCell(int number) {
        this.number = number;
    }

    @Override
    public int getResult() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
