package model;

import java.io.Serializable;

/**
 * Implementa il tipo cella operazione.
 *
 * @author Mattia Lazzarini
 * @see model.GeneralCell
 * @see java.io.Serializable
 */
public class OperationCell extends GeneralCell implements Serializable {
    /**
     * Risultato dell'operazione.
     */
    private int result;
    /**
     * Valore del primo operando.
     */
    private int firstOpValue;
    /**
     * Valore del secondo operando.
     */
    private int secondOpValue;
    /**
     * Tipo di operazione.
     */
    private char op;
    /**
     * Formula.
     */
    private String formula;

    /**
     * Memorizza tutti i valori relativi all'operazione che la riguardano.
     * <p>Salva:</p>
     * <ul><li>Il tipo di operazione</li>
     * <li>La formula completa</li>
     * <li>Il valore del primo operando</li>
     * <li>Il valore del secondo operando</li></ul>
     * <p>Inoltre in base al tipo di operazione effettua e memorizza il risultato dell'operazione.</p>
     * <p>Se viene effettuata una divisione con un denominatore pari a 0 allora viene impostato un risultato pari a 0.</p>
     *
     * @param formula       Formula
     * @param op            Tipo di operazione
     * @param firstOpValue  Valore del primo operando
     * @param secondOpValue Valore del secondo operando
     */
    public OperationCell(String formula, char op, int firstOpValue, int secondOpValue) {
        this.op = op;
        this.formula = formula;
        this.firstOpValue = firstOpValue;
        this.secondOpValue = secondOpValue;

        switch (this.op) {
            case '+':
                result = this.firstOpValue + this.secondOpValue;
                break;
            case '-':
                result = this.firstOpValue - this.secondOpValue;
                break;
            case '*':
                result = this.firstOpValue * this.secondOpValue;
                break;
            default:
                try {
                    result = this.firstOpValue / this.secondOpValue;
                } catch (ArithmeticException e) {
                    result = 0;
                }
                break;
        }
    }

    /**
     * Ritorna il risultato dell'operazione.
     *
     * @return Risultato
     */
    @Override
    public int getResult() {
        return result;
    }

    /**
     * Ritorna l'operazione.
     *
     * @return Operazione
     */
    public String getFormula() {
        return formula;
    }

    /**
     * Ritorna il risultato sottoforma di stringa.
     *
     * @return Risultato memorizzato di tipo stringa
     */
    @Override
    public String toString() {
        return Integer.toString(result);
    }
}
