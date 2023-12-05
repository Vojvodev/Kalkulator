package tests;


import calculators.Calculator;
import exceptions.*;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
 

/**
 * Unit tests for Calculator.
 * Used Junit5 and Hamcrest2.2. All tests passed with 100% coverage.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception{

    }

    @BeforeEach
    void setUp() throws Exception{
        calculator.setCurrentValue(0.0);
    }

    @AfterEach
    void tearDown() throws Exception{

    }

    
    @Test
    public void testCalculator(){
        Calculator calc1 = new Calculator();
        assertThat("Object should not be null", calc1, notNullValue());
        assertThat("Object should be zero", calc1.getCurrentValue(), is(0.0));
    }

    
    @Test
    public void testGetCurrentValue(){
        assertThat(calculator.getCurrentValue(), is(0.0));

        Calculator calc1 = new Calculator();
        try{
            calc1.setCurrentValue(1.0);
        }
        catch (NullValueException e) {
        	assertThat(e.getMessage(), false);
        }

        assertThat(calc1.getCurrentValue(), is(1.0));

    }


    @ParameterizedTest
    @MethodSource("setValueParams")
    public void testSetCurrentValue(Double input, Double output){
        assertThat("Should be 0.0", calculator.getCurrentValue(), is(0.0));

        try{
            calculator.setCurrentValue(input);
            assertThat("Should be " + input, calculator.getCurrentValue(), is(output));
        }
        catch (NullValueException e) {
        	assertThat(e.getMessage(), false);
        }

        Throwable exception = assertThrows(NullValueException.class, () -> calculator.setCurrentValue(null));
        assertThat(exception, instanceOf(NullValueException.class));
    }

    private static Stream<Arguments> setValueParams(){
        return Stream.of(
                Arguments.of(-1.0, -1.0),
                Arguments.of( 999.99, 999.99),
                Arguments.of( 0.0, 0.0)
        );
    }


    /**
     * Testing "good" or useful behavior of the method <b>Calculator.calculate()</b>.  
     * 
     * @param input Value parameter used for the method.
     * @param operator The operator used for the method.
     * @param curr The currentValue.
     * @param result The resulting value.
     * @since 1.0
     */
    @ParameterizedTest
    @MethodSource("calculateParamsGood")
    public void testCalculateGood(Double input, char operator, Double curr, Double result){
        try {
        	calculator.setCurrentValue(curr);
            calculator.calculate(input, operator);
        }catch (Exception e) {
        	assertThat(e.getMessage(), false);
        	}

        assertThat(calculator.getCurrentValue(), is(result));
    }

    private static Stream<Arguments> calculateParamsGood(){
        return Stream.of(
                Arguments.of(25.0, '+', 0.0, 25.0),
                Arguments.of(26.0, '-', 1.0, -25.0),
                Arguments.of(2.0, '*', 0.0, 0.0),
                Arguments.of(5.0, '*', 7.5, 37.5),
                Arguments.of(-1.0, '*', 0.5, -0.5),
                Arguments.of(2.0, '/', 0.0, 0.0),
                Arguments.of(3.0, '/', 9.0, 3.0),
                Arguments.of(-2.0, '/', -16.0, 8.0)
        );
    }

    
    /**
     * Testing the method <b>Calculator.calculate()</b> with "bad" or invalid data.
     * Checking if the exceptions get thrown correctly.
     * 
     * @param input The value parameter used for the method.
     * @param operator The operator used for the method.
     * @param expected The exception that is expected to be thrown.
     * @since 1.0
     */
    @ParameterizedTest
    @MethodSource("calculateParamsBad")
    public void testCalculateBad(Double input, char operator, Class<? extends Throwable> expected){
        Throwable exception = assertThrows(expected, () -> calculator.calculate(input, operator));
        assertThat(exception, instanceOf(expected));
    }

    private static Stream<Arguments> calculateParamsBad(){
        return Stream.of(
                Arguments.of(null, '+', NullValueException.class),
                Arguments.of(0.0, '/', DivisionByZeroException.class),
                Arguments.of(2.0, '|', NotSupportedOperationException.class)
        );
    }


}
