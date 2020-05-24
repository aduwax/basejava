package ru.javawebinar.basejava;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object QUEUE_A = new Object();
    private static final Object QUEUE_B = new Object();


    public static void main(String[] args) throws InterruptedException {
        MainConcurrency mainConcurrency = new MainConcurrency();
        mainConcurrency.deadlock();
//        System.out.println(Thread.currentThread().getName());
//
//        Thread thread0 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
//            }
//        };
//        thread0.start();
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
//            }
//
//            private void inc() {
//                synchronized (this) {
////                    counter++;
//                }
//            }
//
//        }).start();
//
//        System.out.println(thread0.getState());
//
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
//
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//            });
//            thread.start();
//            threads.add(thread);
//        }
//
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(mainConcurrency.counter);
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }

    public void deadlock(){

        Thread thread1 = new Thread(() -> {
            try {
                action1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                action2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public void action1() throws InterruptedException {
        synchronized (QUEUE_A) {
            Thread.sleep(1000);
            action2();
        }
    }

    public void action2() throws InterruptedException {
        synchronized (QUEUE_B) {
            Thread.sleep(1000);
            action1();
        }
    }
}