package com.antonio.executor;

import java.util.concurrent.*;

public class SumCalculator implements Callable<Integer> {

    private int number1;
    private int number2;

    public SumCalculator(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Calculating sum of " + number1 + " and " + number2 + " in thread: " + Thread.currentThread().getName());
        Thread.sleep(2000); // Simulate a time-consuming task
        int sum = number1 + number2;
        System.out.println("Sum calculated: " + sum + " in thread: " + Thread.currentThread().getName());
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> sumCalculator = new SumCalculator(1, 2);
        Future<Integer> result = executorService.submit(sumCalculator);

        while (!result.isDone()) {
            System.out.println("Waiting for the result...");
            try {
                Thread.sleep(500); // Check every 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Result of the sum: " + result.get() + " in thread: " + Thread.currentThread().getName());
        executorService.shutdown();
    }
}
