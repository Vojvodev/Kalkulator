package tests;


import calculators.CalculatorAdvanced;
import exceptions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;


/**
 * Unit tests for CalculatorAdvanced.
 * Used Junit5 and Hamcrest2.2. All tests passed with 100% coverage.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class CalculatorAdvancedTest {

    CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

    @BeforeAll
    static void setUpBeforeClass() throws Exception{

    }

    @AfterAll
    static void tearDownAfterClass() throws  Exception{

    }

    @BeforeEach
    void setUp() throws Exception{
        calculatorAdvanced.setCurrentValue(0.0);
    }

    @AfterEach
    void tearDown() throws Exception{

    }


    /**
     * Testing "good" or useful behavior of the method <b>CalculatorAdvanced.calculateAdvanced()</b>. 
     * 
     * @param action The action used in method.
     * @param curr The <b>currentValue</b>.
     * @param result The resulting value.
     * @since 1.0
     */
    @ParameterizedTest
    @MethodSource("calculateParamsGood")
    public void testCalculateAdvancedGood(char action, Double curr, Double result){
        try {
            calculatorAdvanced.setCurrentValue(curr);
            calculatorAdvanced.calculateAdvanced(action);
            assertThat("Should be " + result, calculatorAdvanced.getCurrentValue(), is(result));
        }
        catch (Exception e) {
        	assertThat(e.getMessage(), false);
        }
    }

    private static Stream<Arguments> calculateParamsGood(){
        return Stream.of(
                Arguments.of('!', 0.0, 1.0),
                Arguments.of('!', 0.5, 1.0),
                Arguments.of('!', 1.0, 1.0),
                Arguments.of('!', 9.5, 362880.0),
                Arguments.of('!', 10.0, 3628800.0),

                Arguments.of('0', -0.9, 1.0),
                Arguments.of('0', 16.0, 1.0),
                Arguments.of('0', 0.0, 1.0),
                Arguments.of('1', -0.5, 0.0),		// Is zero because of the rounding that's done (-0.5)^2 -> (0)^2 = 0
                Arguments.of('2', 0.0, 0.0),		// same
                Arguments.of('3', 0.9, 0.0),		// same
                Arguments.of('4', 2.0, 16.0),
                Arguments.of('5', 3.0, 243.0),
                Arguments.of('9', 1.0, 1.0)
        );
    }


    /**
     * Testing the method <b>CalculatorAdvanced.calculateAdvanced()</b> with "bad" or invalid data.
     * Checking if the exceptions get thrown correctly.
     * 
     * @param action The action used in the method.
     * @param curr The <b>currentValue</b>.
     * @param expected The exception that is expected to be thrown.
     * @since 1.0
     */
    @ParameterizedTest
    @MethodSource("calculateParamsBad")
    public void testCalculateAdvancedBad(char action, Double curr, Class<? extends Throwable> expected){
        try {
            calculatorAdvanced.setCurrentValue(curr);
            Throwable exception = assertThrows(expected, () -> calculatorAdvanced.calculateAdvanced(action));
            assertThat(exception, instanceOf(expected));
        }
        catch (Exception e) {
        	assertThat(e.getMessage(), false);
        }
    }

    private static Stream<Arguments> calculateParamsBad(){
        return Stream.of(
                Arguments.of('?', 0.0, NotSupportedOperationException.class),
                Arguments.of('!', -0.5, NumberNotInAreaException.class),
                Arguments.of('!', 11.0, NumberNotInAreaException.class)
        );
    }


    /**
     * Testing "good" or useful behavior of the method <b>CalculatorAdvanced.hasCharacteristicd()</b>. 
     * 
     * @param value The value used for the method.
     * @param curr The <b>currentValue</b>.
     * @param result The resulting value.
     * @since 1.0
     */
    @ParameterizedTest
    @MethodSource("characteristicParamsGood")
    public void testHasCharacteristicGood(char value, Double curr, Boolean result){
        try {
            calculatorAdvanced.setCurrentValue(curr);
            assertThat("Should be true", calculatorAdvanced.hasCharacteristic(value), is(result));
        }
        catch (Exception e) {
        	assertThat(e.getMessage(), false);
        }
    }

    private static Stream<Arguments> characteristicParamsGood(){
        return Stream.of(
                Arguments.of('A', 1.0,   true),
                Arguments.of('A', 9.0,   true),
                Arguments.of('A', 153.0, true),
                Arguments.of('A', 370.0, true),

                Arguments.of('A', 10.0,  false),
                Arguments.of('A', 154.0, false),
                Arguments.of('A', 152.0, false),

                Arguments.of('P', 6.0,    true),
                Arguments.of('P', 28.0,   true),
                Arguments.of('P', 496.0,  true),
                Arguments.of('P', 8128.0, true),

                Arguments.of('P', 1.0,   false),
                Arguments.of('P', 5.0,   false),
                Arguments.of('P', 497.0, false)
        );
    }


    /**
     * Testing the method <b>CalculatorAdvanced.hasCharacteristicd()</b> with "bad" or invalid data.
     * Checking if the exceptions get thrown correctly.
     * 
     * @param value The value used in the method.
     * @param curr The <b>currentValue</b>.
     * @param expected The exception that is expected to be thrown.
     * @since 1.0
     */
    @ParameterizedTest
    @MethodSource("characteristicParamsBad")
    public void testHasCharacteristicBad(char value, Double curr, Class<? extends Throwable> expected){
        try {
            calculatorAdvanced.setCurrentValue(curr);
            Throwable exception = assertThrows(expected, () -> calculatorAdvanced.hasCharacteristic(value));
            assertThat(exception, is(instanceOf(expected)));
        }
        catch (Exception e) {
        	assertThat(e.getMessage(), false);
        }
    }

    private static Stream<Arguments> characteristicParamsBad(){
        return Stream.of(
                Arguments.of('a', 1.0,   NotSupportedOperationException.class),
                Arguments.of('p', 9.0,   NotSupportedOperationException.class),
                Arguments.of('A', 0.9,   NumberNotInAreaException.class),
                Arguments.of('A', -11.0, NumberNotInAreaException.class),
                Arguments.of('P', 0.9,   NumberNotInAreaException.class),
                Arguments.of('P', -14.0, NumberNotInAreaException.class)
        );
    }


}
