package group4;

import group4.exceptions.RethrowExceptionHandler;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Energy Management System!");

        try {
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Create daily logs");
                System.out.println("2. View log file");
                System.out.println("3. Simulate data exchange");
                System.out.println("4. Delete a log file");
                System.out.println("5. Move a log file");
                System.out.println("6. Rename a log file");
                System.out.println("7. Trigger Rethrow Exception");
                System.out.println("8. Start multithreaded battery charging and usage simulation");
                System.out.println("9. Exit");

                int choice = 0;
                boolean validInput = false;

                // Input validation loop
                while (!validInput) {
                    try {
                        choice = scanner.nextInt();
                        validInput = true;  // valid input, exit the loop
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 9.");
                        scanner.nextLine();  // clear the invalid input
                    }
                }
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        try {
                            DailyLogCreator.createSystemLogs();
                        } catch (IOException e) {
                            System.out.println("Error creating system logs: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Enter equipment name: ");
                        String equipmentName = scanner.nextLine();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        LogViewer.openLogFile(equipmentName, date);
                        break;
                    case 3:
                        System.out.print("Simulate error in data exchange? (true/false): ");
                        boolean triggerError = scanner.nextBoolean();
                        scanner.nextLine(); // consume the newline
                        try {
                            DataExchangeSimulation.simulateDataExchange(triggerError);
                        } catch (IOException e) {
                            System.out.println("Error during data exchange: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter log file name to delete (e.g., ChargingStation1_log_2024-10-06.txt): ");
                        String fileNameToDelete = scanner.nextLine();
                        LogFileManager.deleteLogFile(fileNameToDelete);
                        break;
                    case 5:
                        System.out.print("Enter log file name to move (e.g., ChargingStation1_log_2024-10-06.txt): ");
                        String fileNameToMove = scanner.nextLine();
                        System.out.print("Enter destination directory (e.g., logs/archive): ");
                        String destinationDirectory = scanner.nextLine();
                        try {
                            LogFileManager.moveLogFile(fileNameToMove, destinationDirectory);
                        } catch (IOException e) {
                            System.out.println("Error moving file: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.print("Enter current log file name (e.g., ChargingStation1_log_2024-10-06.txt): ");
                        String oldFileName = scanner.nextLine();
                        System.out.print("Enter new log file name (e.g., ChargingStation1_log_2024-10-07.txt): ");
                        String newFileName = scanner.nextLine();
                        LogFileManager.renameLogFile(oldFileName, newFileName);
                        break;
                    case 7:
                        try {
                            RethrowExceptionHandler handler = new RethrowExceptionHandler();
                            handler.rethrowException();
                        } catch (IOException e) {
                            System.out.println("Caught re-thrown exception: " + e.getMessage());
                            e.printStackTrace();  // Log the stack trace
                        }
                        break;
                    case 8:
                        Battery battery = new Battery();
                        BatteryCharger charger = new BatteryCharger(battery);
                        BatteryUser user = new BatteryUser(battery);

                        Thread chargerThread = new Thread(charger);
                        Thread userThread = new Thread(user);

                        chargerThread.start();
                        userThread.start();

                        System.out.println("Started multithreaded battery charging and usage simulation.");
                        System.out.println("Press Enter to stop the simulation and go back to the main menu...");

                        // Wait for Enter to stop the simulation
                        scanner.nextLine();
                        
                        // Stop the threads
                        charger.stop();
                        user.stop();

                        // Interrupt threads in case they are sleeping
                        chargerThread.interrupt();
                        userThread.interrupt();

                        try {
                            chargerThread.join();
                            userThread.join();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        System.out.println("Simulation stopped.");
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }

                // Prompt user to press Enter to go back to the main menu
                System.out.println("\nPress Enter to go back to the main menu...");
                scanner.nextLine();  // Wait for Enter key press
            }
        } finally {
            scanner.close();  // Close the scanner
        }
    }
}
