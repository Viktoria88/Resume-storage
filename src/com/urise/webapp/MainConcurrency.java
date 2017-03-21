package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by viktoriyasidenko on 3/16/17.
 */
public class MainConcurrency {

    private static int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
//    private static final Object LOCK = new Object();
//    private static final Object DEAD_LOCK = new Object();
    private static final int THREADS_NUMBER = 10000;
//    private static final Lock lock = new ReentrantLock();

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };


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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++){
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
//            completionService.poll();
 /*
            Thread thread = new Thread(() -> {
            for(int j = 0; j < 100; j++){
                mainConcurrency.inc();
            }
            latch.countDown();
            });
            thread.start();
//            threads.add(thread);
//            thread.join();
*/
        }

//        Thread.sleep(500);
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                    e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(mainConcurrency.counter);
        System.out.println(mainConcurrency.atomicCounter.get());

        new MainConcurrency().inc();

//        Thread t1 = new Thread(){
//            @Override
//            public void run(){
//                synchronized (LOCK){
//                    Thread.yield();
//                    synchronized (DEAD_LOCK){
//                        System.out.println("Success!");
//                    }
//                }
//            }
//        };
//
//        Thread t2 = new Thread(){
//            @Override
//            public void run(){
//                synchronized (DEAD_LOCK){
//                    Thread.yield();
//                    synchronized (LOCK){
//                        System.out.println("Success!");
//                    }
//                }
//            }
//        };
//
//        t1.start();
//        t2.start();

        final String lock1 = "lock1";
        final String lock2 = "lock2";

        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private static void deadLock(Object lock1, Object lock2){
        new Thread(()->{
            System.out.println("Waiting " + lock1);
            synchronized (lock1){
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2){
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();
    }

//    private static synchronized void inc(){
//        counter++;
//    }

    private /*synchronized*/ void inc() {
//        synchronized (MainConcurrency.class){
//        synchronized (this){
//        synchronized (LOCK){
//            System.out.println(this);
//        wait();
//            notifyAll();
//    }

//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }


        atomicCounter.incrementAndGet();

//        }
    }
}
