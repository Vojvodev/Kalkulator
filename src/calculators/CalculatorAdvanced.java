package calculators;


import exceptions.*;


/**
 * Advanced calculator which provides, besides four basic arithmetic operations,
 * factorial and exponent calculation, along with checking if the number is Armstrong(Narcissistic) or Perfect.
 *<br>
 * -Factorial of a non-negative integer n, denoted by n!, is the product of all positive integers
 * less than or equal to n: The values of 0! and 1! are 1. The value of 3! is 1*2*3 = 6.<br>
 * -A narcissistic number is a number that is the sum of its own digits each raised to the power
 * of the number of digits. It is also called an Armstrong number.<br>
 * -In number theory, a perfect number is a positive integer that is equal to the sum of its positive divisors,
 * excluding the number itself.<br>
 *
 *
 * @author Darijo Prerad
 * @version 1.0
 */
public class CalculatorAdvanced extends Calculator {

    /**
     * Initializes a new CalculatorAdvanced object with a default current value set to 0.0
     * @since 1.0
     */
    public CalculatorAdvanced() {
        super();
    }


    /**
     * Provides factorial and exponent calculation.The decimal number is rounded using only it's whole part.
     * and the results are stored in the current value attribute. Calculating factorial has a limitation:
     * <i>the current value must be non-negative and ≤ 10</i>.
     *
     * @param action The operator that is used for calculation. Valid values are '!' for factorial
     *               and '1', '2', '3', '4', '5', '6', '7', '8', '9' for calculating the n-th power of the
     *               current value, where n is one of the above listed numbers.
     *
     * @throws NotSupportedOperationException If <b>action</b> is not a valid value.
     * @throws NumberNotInAreaException If the current value is not in-between the allowed range.
     *
     * @since 1.0
     */
    public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
        if(action == '!') {
        	// factorial(value) method: 
            if(currentValue < 0.0 || currentValue > 10.0) {
                throw new NumberNotInAreaException("Impossible to calculate factorial of that number!");
            }
            
            int intValue = currentValue.intValue();  				
            
            if(intValue == 0.0 || intValue == 1.0) {
                currentValue = 1.0;
                return;
            }

            double mul = 1.0;
            for(int i = 2; i <= intValue; i++) {
                mul *= i;
            }

            currentValue = mul;
        }
        else if(action >= 0x30 && action <= 0x39){
            int num = action - 48; // (If '0' num = 0) ...
            currentValue = pow(currentValue.longValue(), num);
        }
        else {
            throw new NotSupportedOperationException();
        }


    }

    /**
     * Checks if the whole part of the current value is an Armstrong or a Perfect number. The limitation is:
     * <i>the current value must be ≥ 1.</i>
     *
     * @param value Denotes which check to perform. Valid values are:
     *              'A' to check if the number is an Armstrong number and
     *              'P' to check if the number is Perfect.
     * @return <i>true</i> if the number is an Armstrong number (or perfect, depending on <b>value</b> parameter),<br>
     *         <i>false</i> if the number is not an Armstrong number(or perfect, depending on <b>value</b> parameter).
     * @throws NotSupportedOperationException If <b>value</b> parameter is not a valid value.
     * @throws NumberNotInAreaException If the current value is not in-between the allowed range.
     * @since 1.0
     */
    public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException{
        if(value == 'A') {
            return isArmstrong(currentValue.longValue());
        }
        else if(value == 'P') {
            return isPerfect(currentValue.longValue());
        }
        else {
            throw new NotSupportedOperationException();
        }
    }


    /**
     * Calculates the n-th power of the current value, where n is the <b>num</b> parameter.
     *
     * @param currentValue The value whose power is calculated.
     * @param num The exponent.
     * @return <b>currentValue</b> raised to the power of <b>num</b>.
     * @since 1.0
     */
    private static double pow(long currentValue, int num) {
        if(num == 0) {
            return 1.0;
        }

        double mul = currentValue;

        for(int i = 1; i < num; i++) {
            mul *= currentValue;
        }

        return mul;
    }


    /**
     * Checks if the number is an Armstrong number.
     *
     * @param curr The number that will be checked.
     * @return <i>true</i> if the number <b>is</b> an Armstrong number,<br>
     *         <i>false</i> if the number <b>is not</b> an Armstrong number.
     * @throws NumberNotInAreaException If <b>curr</b> is less than 1.0.
     * since 1.0
     */
    private static boolean isArmstrong(long curr) throws NumberNotInAreaException{
        if(curr < 1.0) {
            throw new NumberNotInAreaException();
        }

        int numDigits = 0;
        long temp = curr;

        for(; temp != 0; temp /= 10) {
            numDigits++;
        }

        int sum = 0;
        temp = curr;
        for(; temp != 0; temp /= 10) {
            sum += pow((temp % 10), numDigits);
        }

        return sum == curr;
    }


    /**
     * Checks if the number is perfect.
     *
     * @param curr The number that will be checked.
     * @return <i>true</i> if the number <b>is</b> perfect,<br>
     *         <i>false</i> if the number <b>is not</b> perfect.
     * @throws NumberNotInAreaException If <b>curr</b> is less than 1.0.
     * @since 1.0
     */
    private static boolean isPerfect(long curr) throws NumberNotInAreaException{
        if(curr < 1.0) {
            throw new NumberNotInAreaException();
        }

        long temp = curr;
        int sum = 0;

        for(int i = 1; i <= temp / 2; i++) {
            if(temp % i == 0) sum += i;
        }

        return sum == temp;
    }


}
