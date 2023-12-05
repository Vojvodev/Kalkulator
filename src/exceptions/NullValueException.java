package exceptions;

/**
 * Thrown when an application attempts to use null where an object is required.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class NullValueException extends Exception {

    /**
     * Constructs a new exception with null as its detail message.
     * 
     * @since 1.0
     */
    public NullValueException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param str The detail message.
     * @since 1.0
     */
    public NullValueException(String str){
        super(str);
    }
}
