package group4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class LogFileManager {

    // Method to create log file for a charging station or energy source
    public static void createLogFile(String equipmentName) throws IOException {
        LocalDate today = LocalDate.now();
        String fileName = "logs/" + equipmentName + "_log_" + today + ".txt";
        File logFile = new File(fileName);
        
        if (logFile.createNewFile()) {
            System.out.println("Log file created: " + logFile.getName());
        } else {
            System.out.println("Log file already exists.");
        }
    }

    // Method to delete a log file
    public static void deleteLogFile(String fileName) {
        File logFile = new File("logs/" + fileName);
        
        // Check if the file exists
        if (!logFile.exists()) {
            System.out.println("File not found: " + logFile.getAbsolutePath());
        } else if (logFile.delete()) {
            System.out.println("Deleted the file: " + logFile.getName());
        } else {
            System.out.println("Failed to delete the file: " + logFile.getAbsolutePath());
        }
    }

    // Method to move a log file (used for archiving)
    public static void moveLogFile(String fileName, String newDirectory) throws IOException {
        Path source = Path.of("logs/" + fileName);
        Path destination = Path.of(newDirectory + "/" + fileName);
        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Moved file to: " + destination);
    }

    // Method to rename a log file
    public static void renameLogFile(String oldFileName, String newFileName) {
        File oldFile = new File("logs/" + oldFileName);
        File newFile = new File("logs/" + newFileName);
        
        if (oldFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                System.out.println("Renamed file to: " + newFile.getName());
            } else {
                System.out.println("Failed to rename file.");
            }
        } else {
            System.out.println("File not found: " + oldFile.getAbsolutePath());
        }
    }
}
