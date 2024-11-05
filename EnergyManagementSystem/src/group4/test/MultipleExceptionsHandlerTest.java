package group4.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import group4.exceptions.MultipleExceptionsHandler;

public class MultipleExceptionsHandlerTest {

    @Test
    public void testHandleMultipleExceptions() {
        MultipleExceptionsHandler handler = new MultipleExceptionsHandler();
        assertDoesNotThrow(() -> handler.handleMultipleExceptions());
    }

    @Test
    public void testArithmeticExceptionHandled() {
        MultipleExceptionsHandler handler = new MultipleExceptionsHandler();
        try {
            handler.handleMultipleExceptions();
        } catch (Exception e) {
            assertTrue(e instanceof ArithmeticException, "Should handle ArithmeticException");
        }
    }

    @Test
    public void testIOExceptionHandled() {
        MultipleExceptionsHandler handler = new MultipleExceptionsHandler();
        try {
            handler.handleMultipleExceptions();
        } catch (Exception e) {
            assertTrue(e instanceof IOException, "Should handle IOException");
        }
    }

    @Test
    public void testMessageForArithmeticException() {
        MultipleExceptionsHandler handler = new MultipleExceptionsHandler();
        try {
            handler.handleMultipleExceptions();
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage(), "Message should be '/ by zero'");
        }
    }

    @Test
    public void testNoUnhandledExceptions() {
        MultipleExceptionsHandler handler = new MultipleExceptionsHandler();
        assertDoesNotThrow(() -> handler.handleMultipleExceptions(), "No unhandled exceptions should be thrown");
    }
}
