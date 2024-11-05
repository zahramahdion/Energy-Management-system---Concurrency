package group4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class DailyLogCreator {

    public static void createDailyLog(String equipmentName) throws IOException {
        LocalDate today = LocalDate.now();
        String fileName = "logs/" + equipmentName + "_log_" + today + ".txt";
        File logFile = new File(fileName);
        
        // Create log file and write initial content
        if (logFile.createNewFile()) {
            System.out.println("Daily log file created: " + logFile.getName());
            // Write some initial log content
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile))) {
                writer.write("Log entry for " + equipmentName + " on " + today);
                writer.newLine();  // New line
                writer.write("Initial data recorded...");
                writer.newLine();
                writer.write("End of log entry.");
            } catch (IOException e) {
                System.out.println("Error writing to the log file: " + e.getMessage());
            }
        } else {
            System.out.println("Log file already exists.");
        }
    }

    // Create logs for the whole system, charging stations, and energy sources
    public static void createSystemLogs() throws IOException {
        createDailyLog("System");
        createDailyLog("ChargingStation1");
        createDailyLog("EnergySource1");
    }
}
