package group4;

import java.util.Random;

public class BatteryUser implements Runnable {
    private final Battery battery;
    private final Random random = new Random();
    private volatile boolean running = true; // Flag to control thread execution

    public BatteryUser(Battery battery) {
        this.battery = battery;
    }

    public void stop() {
        running = false; // Method to stop the thread
    }

    @Override
    public void run() {
        while (running) {
            int usageAmount = 1 + random.nextInt(10); // Random usage between 1% and 10%
            battery.use(usageAmount);
            try {
                Thread.sleep(400 + random.nextInt(600)); // Random sleep interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("BatteryUser stopped.");
    }
}
