package exceptions;

/**
 * Thrown when an application attempts to use certain operations on a current value
 * that is not in-between the allowed range.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class NumberNotInAreaException extends Exception{

    /**
     * Constructs a new exception with null as its detail message.
     * 
     * @since 1.0
     */
    public NumberNotInAreaException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param str The detail message.
     * @since 1.0
     */
    public NumberNotInAreaException(String str){
        super(str);
    }
}
