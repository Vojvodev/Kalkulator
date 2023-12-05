package exceptions;

/**
 * Thrown when an application attempts to use division but the current value of the parameter is zero.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class DivisionByZeroException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param str The detail message.
     * @since 1.0
     */
    public DivisionByZeroException(String str){
        super(str);
    }
}
