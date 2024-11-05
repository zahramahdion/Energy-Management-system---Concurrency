package group4.exceptions;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceManagementHandler {

    // Modified method to accept a file path as an argument
    public void manageResources(String filePath) throws IOException {
        if (filePath == null) {
            throw new IOException("Error handling resource: null");
        }

        Path path = Paths.get(filePath);

        // Ensure the file exists, otherwise throw an IOException
        if (!Files.exists(path)) {
            throw new IOException("Error handling resource: " + path.toAbsolutePath());
        }

        // Open the file using a FileReader
        try (FileReader fileReader = new FileReader(path.toFile())) {
            // Handle file reading here
            System.out.println("File read successfully.");
        }
    }
}
