package group4.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import group4.exceptions.ChainingExceptionHandler;

public class ChainingExceptionHandlerTest {

    @Test
    public void testChainExceptions() {
        ChainingExceptionHandler handler = new ChainingExceptionHandler();
        // Test if an exception is thrown
        assertThrows(Exception.class, () -> handler.chainExceptions());
    }

    @Test
    public void testChainedExceptionCause() {
        ChainingExceptionHandler handler = new ChainingExceptionHandler();
        // Test if the exception cause is an ArithmeticException
        Exception exception = assertThrows(Exception.class, () -> handler.chainExceptions());
        assertTrue(exception.getCause() instanceof ArithmeticException, 
                   "Chained exception should be caused by ArithmeticException");
    }

    @Test
    public void testChainedExceptionMessage() {
        ChainingExceptionHandler handler = new ChainingExceptionHandler();
        // Test the message of the chained exception
        Exception exception = assertThrows(Exception.class, () -> handler.chainExceptions());
        assertEquals("Chained exception occurred", exception.getMessage(), 
                     "The exception message should be 'Chained exception occurred'");
    }

    @Test
    public void testCauseMessage() {
        ChainingExceptionHandler handler = new ChainingExceptionHandler();
        // Test the message of the cause (ArithmeticException)
        Exception exception = assertThrows(Exception.class, () -> handler.chainExceptions());
        assertEquals("/ by zero", exception.getCause().getMessage(), 
                     "The cause of the exception should be '/ by zero'");
    }

    @Test
    public void testNoUnhandledExceptions() {
        ChainingExceptionHandler handler = new ChainingExceptionHandler();
        // Test to ensure no unhandled exceptions remain
        assertDoesNotThrow(() -> {
            try {
                handler.chainExceptions();
            } catch (Exception e) {
                // Expected exception
            }
        }, "No unhandled exceptions should be thrown");
    }
}
