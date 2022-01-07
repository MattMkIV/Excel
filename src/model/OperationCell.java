package model;

import java.io.Serializable;

public class OperationCell extends GeneralCell implements Serializable {
    private int result;
    private int firstOpValue;
    private int secondOpValue;
    private char op;
    private String formula;

    public OperationCell(String formula, char op, int firstOpValue, int secondOpValue) {
        this.op = op;
        this.formula = formula;
        this.firstOpValue = firstOpValue;
        this.secondOpValue = secondOpValue;

        if (this.op == '+')
            result = this.firstOpValue + this.secondOpValue;
        else if (this.op == '-')
            result = this.firstOpValue - this.secondOpValue;
        else if (this.op == '*')
            result = this.firstOpValue * this.secondOpValue;
        else {
            try {
                result = this.firstOpValue / this.secondOpValue;
            }
            catch (ArithmeticException e) {
                result = 0;
            }
        }
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }
}
