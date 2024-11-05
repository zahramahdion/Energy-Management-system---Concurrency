package group4.exceptions;

import java.io.IOException;

public class MultipleExceptionsHandler {
    public void handleMultipleExceptions() {
        try {
            throw new IOException("IO Exception Occurred");
        } catch (ArithmeticException | IOException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
