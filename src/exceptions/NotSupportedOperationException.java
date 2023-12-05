package exceptions;

/**
 * Thrown when an application attempts to use an operation that is not allowed.
 * 
 * @author Darijo Prerad
 * @version 1.0
 */
public class NotSupportedOperationException extends Exception{

    /**
     * Constructs a new exception with null as its detail message.
     * 
     * @since 1.0
     */
    public NotSupportedOperationException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param str The detail message.
     * @since 1.0
     */
    public NotSupportedOperationException(String str){
        super(str);
    }
}
