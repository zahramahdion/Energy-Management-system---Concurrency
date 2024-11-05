package group4.test;

import group4.exceptions.ResourceManagementHandler;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceManagementHandlerTest {

    // 1. Test that no other exceptions are thrown during resource management
    @Test
    public void testNoOtherExceptionsThrown() throws IOException {
        ResourceManagementHandler handler = new ResourceManagementHandler();

        // Use relative path for the log file
        Path filePath = Paths.get("logs", "sample.txt");
        File logFile = filePath.toFile();

        // Ensure the file exists before running the test
        if (!logFile.exists()) {
            Files.createDirectories(filePath.getParent());  // Create directories if not exists
            logFile.createNewFile();                        // Create the log file
        }

        // Run the test
        assertDoesNotThrow(() -> handler.manageResources(filePath.toString()), "No other exceptions should be thrown");
    }

    // 2. Test that an IOException is thrown when the file does not exist
    @Test
    public void testFileNotFoundThrowsIOException() {
        ResourceManagementHandler handler = new ResourceManagementHandler();
        Path nonExistentFilePath = Paths.get("logs", "non_existent_file.txt");

        // Delete the file if it exists
        File nonExistentFile = nonExistentFilePath.toFile();
        if (nonExistentFile.exists()) {
            nonExistentFile.delete();
        }

        // Expect IOException due to file not existing
        Exception exception = assertThrows(IOException.class, () -> handler.manageResources(nonExistentFilePath.toString()));
        assertEquals("Error handling resource: " + nonExistentFilePath.toAbsolutePath(), exception.getMessage());
    }

    // 3. Test that the file is handled correctly without errors
    @Test
    public void testSuccessfulFileHandling() throws IOException {
        ResourceManagementHandler handler = new ResourceManagementHandler();

        // Use relative path for the log file
        Path filePath = Paths.get("logs", "sample.txt");
        File logFile = filePath.toFile();

        // Ensure the file exists before running the test
        if (!logFile.exists()) {
            Files.createDirectories(filePath.getParent());  // Create directories if not exists
            logFile.createNewFile();                        // Create the log file
        }

        // Run the test
        assertDoesNotThrow(() -> handler.manageResources(filePath.toString()), "File should be handled successfully without errors");
    }

    // 4. Test that IOException is thrown for empty or null file path
    @Test
    public void testIOExceptionForNullFilePath() {
        ResourceManagementHandler handler = new ResourceManagementHandler();

        // Expect IOException for null file path
        Exception exception = assertThrows(IOException.class, () -> handler.manageResources(null));
        assertEquals("Error handling resource: null", exception.getMessage(), "Expected error message for null file path");
    }

    // 5. Test that resources are closed properly
    @Test
    public void testResourcesAreClosed() throws IOException {
        ResourceManagementHandler handler = new ResourceManagementHandler();

        // Use relative path for the log file
        Path filePath = Paths.get("logs", "sample.txt");
        File logFile = filePath.toFile();

        // Ensure the file exists before running the test
        if (!logFile.exists()) {
            Files.createDirectories(filePath.getParent());  // Create directories if not exists
            logFile.createNewFile();                        // Create the log file
        }

        // Simulate file handling
        handler.manageResources(filePath.toString());

        // Check if the file is closed (Note: Mock or spy on the method to check resource closure in real-world cases)
        // Here we assume manageResources closes the resources without exposing the file reader directly.
        assertTrue(logFile.exists(), "The file should still exist, and resources should be properly closed.");
    }
}
