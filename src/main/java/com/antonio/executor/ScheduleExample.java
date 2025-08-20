package com.antonio.executor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.schedule( () -> {
            System.out.println("Task executed after 4 seconds in thread: " + Thread.currentThread().getName());
        }, 4, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown(); // Closes the executor service to prevent new tasks from being submitted


        Timer timer = new Timer(); // Create a Timer instance
        timer.schedule(new TimerTask() { // Task to be scheduled
            @Override
            public void run() {
                System.out.println("Task executed after 2 seconds using Timer in thread: " + Thread.currentThread().getName());
            }
        }, 2000); // Schedule the task to run after 2 seconds

        // Note: Timer does not need to be shut down like ScheduledExecutorService, but you can cancel it if needed

        Runnable task = new Runnable() {
            int counter = 0;
            @Override
            public void run() {
                System.out.println("Task executed immediately in thread: " + Thread.currentThread().getName());
                counter++;
                if(counter > 3){
                    System.out.println("Stopping the task after 3 executions in thread: " + Thread.currentThread().getName());
                    // You can stop the task by not scheduling it again or by using a flag
                    scheduledExecutorService.shutdown();
                }
            }
        };

        scheduledExecutorService.scheduleAtFixedRate( () -> {
            System.out.println("Scheduled task executed every 3 seconds in thread: " + Thread.currentThread().getName());
            task.run();
        }, 0, 3, TimeUnit.SECONDS); // Schedule a task to run every 3 seconds
    }
}
