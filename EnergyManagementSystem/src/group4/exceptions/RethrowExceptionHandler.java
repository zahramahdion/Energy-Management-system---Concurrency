package group4.exceptions;

import java.io.IOException;

public class RethrowExceptionHandler {
    public void rethrowException() throws IOException {
        try {
            // Throw the original exception
            throw new IOException("Initial Exception");
        } catch (IOException e) {
            // Catch and rethrow the same exception
            System.out.println("Caught and rethrowing exception");
            throw e; // Rethrowing the exception
        }
    }
}
