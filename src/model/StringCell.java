package model;

import java.io.Serializable;

public class StringCell extends GeneralCell implements Serializable {
    private String string;

    public StringCell(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
