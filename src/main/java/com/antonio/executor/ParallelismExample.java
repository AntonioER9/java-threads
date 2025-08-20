package com.antonio.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelismExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ejecutando newSingleThreadExecutor");
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        executorService.submit(()->{
            task("Executando Tarea A");
        });
        executorService.submit(()->{
            task("Executando Tarea B");
        });
        executorService.submit(()->{
            task("Executando Tarea C");
        });

        executorService.shutdown(); // Closes the executor service to prevent new tasks from being submitted

        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();

        System.out.println("Tiempo total de ejecución: " + (end - start) + " ms");
        System.out.println("Fin del hilo principal: " + Thread.currentThread().getName());

    }

    public static void task(String name){
        System.out.println("iniciando la tarea "+ Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
