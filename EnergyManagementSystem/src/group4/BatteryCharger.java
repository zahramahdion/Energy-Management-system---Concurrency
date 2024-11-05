package group4;

import java.util.Random;

public class BatteryCharger implements Runnable {
    private final Battery battery;
    private final Random random = new Random();
    private volatile boolean running = true; // Flag to control thread execution

    public BatteryCharger(Battery battery) {
        this.battery = battery;
    }

    public void stop() {
        running = false; // Method to stop the thread
    }

    @Override
    public void run() {
        while (running) {
            int chargeAmount = 5 + random.nextInt(26); // Random charge between 5% and 30%
            battery.charge(chargeAmount);
            try {
                Thread.sleep(800 + random.nextInt(700)); // Random sleep interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("BatteryCharger stopped.");
    }
}
