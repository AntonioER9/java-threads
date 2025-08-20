package com.antonio.executor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskOnSpecificDate {
    public static void main(String[] args) {

        LocalDateTime datetime = LocalDateTime.of(2025,5,14,10,9);

        LocalDateTime now = LocalDateTime.now();

        long delay = Duration.between(now, datetime).toMillis();;

        if (delay < 0) {
            System.out.println("La fecha especificada ya ha pasado.");
            return;
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.schedule( () -> {
            System.out.println("Task executed at " + datetime + " in thread: " + Thread.currentThread().getName());
            scheduledExecutorService.shutdown(); // Closes the executor service to prevent new tasks from being submitted
        }, delay, TimeUnit.MILLISECONDS);


    }
}
