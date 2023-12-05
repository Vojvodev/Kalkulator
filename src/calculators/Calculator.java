package calculators;


import exceptions.*;


/**
 * Calculator class that provides four basic arithmetic operations.
 * The calculator stores only one value, which means it always works with just two values.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class Calculator {
    /**
     * Stored value inside the calculator and always the first operand.
     */
    protected Double currentValue;


    /**
     * Initializes a new Calculator object with a default current value set to 0.0
     * @since 1.0
     */
    public Calculator() {
        this.currentValue = 0.0;
    }


    /**
     * Getter for the current value.
     *
     * @return The current value.
     * @since 1.0
     */
    public Double getCurrentValue() {
        return currentValue; // to do: don't return null
    }

    /**
     * Setter for the current value. Value should not be null.
     *
     * @param value  The value that is used to set the current value to.
     * @throws NullValueException  If the passed parameter is null.
     * @since 1.0
     */
    public void setCurrentValue(Double value) throws NullValueException{
        if(value == null) throw new NullValueException("Could not set currentValue to null!");
        currentValue = value;
    }


    /**
     * Provides four basic arithmetic operations: addition, subtraction, multiplication and division.
     * The resulting value is set to <b>currentValue</b>.
     *
     * @param value  The second operand in the mathematical expression.
     * @param operator  The operator in-between the operands. Valid values are: '+', '-', '*' and '/'.
     * @throws NotSupportedOperationException  If <b>operator</b> is not a valid value.
     * @throws DivisionByZeroException  If <b>value</b> is zero and <b>operator</b> is '/'.
     * @throws NullValueException  If <b>value</b> is null.
     * @since 1.0
     */
    public void calculate(Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException, NullValueException{
        if(value == null) {
            throw new NullValueException();
        }


        if(operator == '+') {
            currentValue += value;
        }
        else if(operator == '-') {
            currentValue -= value;
        }
        else if(operator == '*') {
            currentValue *= value;
        }
        else if(operator == '/') {
            if(value == 0.0) {
                throw new DivisionByZeroException("You can not divide by zero!");
            }

            currentValue /= value;
        }
        else {
            throw new NotSupportedOperationException("You can only use +, -, * or / in Calculator!");
        }

    }
}
