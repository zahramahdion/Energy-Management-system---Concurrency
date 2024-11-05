package group4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogViewer {

    // Method to allow users to open logs by name or date
    public static void openLogFile(String equipmentName, String date) {
        String fileName = "logs/" + equipmentName + "_log_" + date + ".txt";
        BufferedReader reader = null;
        try {
            // Try to open and read the file
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // Handle file not found error
            System.out.println("Log file not found: " + fileName);
        } catch (IOException e) {
            // Handle other I/O errors
            System.out.println("An error occurred while reading the log file: " + e.getMessage());
        } finally {
            // Close the reader if it was opened
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to close the file: " + e.getMessage());
            }
        }
    }

    // Use regex to validate date format (YYYY-MM-DD)
    public static boolean validateDate(String date) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask user for equipment name and date
            System.out.print("Enter equipment name (e.g., ChargingStation1): ");
            String equipmentName = scanner.nextLine();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            if (validateDate(date)) {
                openLogFile(equipmentName, date);
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } finally {
            scanner.close();  // Close the scanner
        }
    }
}
