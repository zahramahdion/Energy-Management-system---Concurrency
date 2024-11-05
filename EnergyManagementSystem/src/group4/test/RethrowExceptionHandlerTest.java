package group4.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import group4.exceptions.RethrowExceptionHandler;

import java.io.IOException;

public class RethrowExceptionHandlerTest {

    @Test
    public void testRethrowException() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Test that the IOException is thrown
        assertThrows(IOException.class, () -> handler.rethrowException(), "Expected IOException to be thrown");
    }

    @Test
    public void testExceptionMessage() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Test that the exception message matches
        Exception exception = assertThrows(IOException.class, () -> handler.rethrowException());
        assertEquals("Initial Exception", exception.getMessage(), "Exception message should be 'Initial Exception'");
    }

    @Test
    public void testRethrowExceptionNotNull() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Ensure the exception is not null
        Exception exception = assertThrows(IOException.class, () -> handler.rethrowException());
        assertNotNull(exception, "Exception should not be null");
    }

    @Test
    public void testCaughtAndRethrown() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Test that the caught exception has the expected message
        try {
            handler.rethrowException();
        } catch (IOException e) {
            assertEquals("Initial Exception", e.getMessage(), "Caught and rethrown exception should have message 'Initial Exception'");
        }
    }

    @Test
    public void testNoOtherExceptionsThrown() {
        RethrowExceptionHandler handler = new RethrowExceptionHandler();
        // Ensure that no other exceptions are thrown
        assertDoesNotThrow(() -> {
            try {
                handler.rethrowException();
            } catch (IOException e) {
                // Expected IOException
            }
        }, "No other exceptions should be thrown");
    }
}
