package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viktoriyasidenko on 3/16/17.
 */
public class MainConcurrency {

    private static int counter;
    private static final Object LOCK = new Object();
    private static final Object DEAD_LOCK = new Object();
    private static final int THREADS_NUMBER = 10000;


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
            private void inc(){
                synchronized (this){
                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());


        System.out.println("Многопоточность, синхронизация");

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++){
            Thread thread = new Thread(() -> {
            for(int j = 0; j < 100; j++){
                mainConcurrency.inc();
            }
            });
            thread.start();
            threads.add(thread);
//            thread.join();
        }

//        Thread.sleep(500);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        });
        System.out.println(counter);

        new MainConcurrency().inc();

        Thread t1 = new Thread(){
            @Override
            public void run(){
                synchronized (LOCK){
                    Thread.yield();
                    synchronized (DEAD_LOCK){
                        System.out.println("Success!");
                    }
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run(){
                synchronized (DEAD_LOCK){
                    Thread.yield();
                    synchronized (LOCK){
                        System.out.println("Success!");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }

//    private static synchronized void inc(){
//        counter++;
//    }

    private synchronized void inc() {
//        synchronized (MainConcurrency.class){
//        synchronized (this){
//        synchronized (LOCK){
//            System.out.println(this);
            counter++;
//            wait();
//            notifyAll();
//        }
    }
}
