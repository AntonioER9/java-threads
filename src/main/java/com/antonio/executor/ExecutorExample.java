package com.antonio.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> System.out.println("Task 1 is running in thread: " + Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println("Task 2 is running in thread: " + Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println("Task 3 is running in thread: " + Thread.currentThread().getName()));
        executorService.shutdown();
    }

    public static void main2(String[] args) {

        Runnable task1 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Task 1 - Message: " + i + " running in: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };


    }

}
