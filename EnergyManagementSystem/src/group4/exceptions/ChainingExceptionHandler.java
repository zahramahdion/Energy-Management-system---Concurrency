package group4.exceptions;

public class ChainingExceptionHandler {

    public void chainExceptions() throws Exception {
        try {
            // Simulate an ArithmeticException (e.g., division by zero)
            int result = 10 / 0;  // This will trigger an ArithmeticException

            // Option: Use the result for debugging or further logic:
            System.out.println("Result: " + result);  // Print the result (for debugging or use in logic)
        } catch (ArithmeticException e) {
            // Chain the ArithmeticException with a new exception
            throw new Exception("Chained exception occurred", e);
        }
    }
}
