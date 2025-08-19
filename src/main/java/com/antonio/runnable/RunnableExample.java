package com.antonio.runnable;

public class RunnableExample implements Runnable {
    private String name;

    public RunnableExample(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + " Mensaje: " + i + " Ejecutando en: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new RunnableExample("thread1"));
        Thread thread2 = new Thread(new RunnableExample("thread2"));

        thread1.start();
        thread2.start();

        System.out.println("Fin del hilo principal");

    }
}
