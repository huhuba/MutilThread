package zss;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {
    //线程池中过的线程执行完任务后，是怎么从队列中取新的任务执行的。
    public static void main(String[] args) {
        ArrayBlockingQueue qu = new ArrayBlockingQueue<>(3);
        ThreadPoolExecutor ex = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, qu);
        ex.execute(new MyThread3("1"));
        ex.execute(new MyThread3("2"));
        ex.execute(new MyThread3("3"));

        ex.execute(new MyThread3("4"));
        ex.execute(new MyThread3("5"));
        ex.execute(new MyThread3("6"));

        ex.execute(new MyThread3("7"));
        ex.execute(new MyThread3("8"));
        ex.execute(new MyThread3("9"));
        ex.execute(new MyThread3("10"));
//        ex.shutdown();

/*
        ex.execute(new MyThread2("10"));
        ex.execute(new MyThread2("11"));
        ex.execute(new MyThread2("12"));*/
    }
}

class MyThread2 extends Thread {
    public String name;

    public MyThread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("MyThread2 test  name:" + name);
        try {
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}class MyThread3 extends Thread {
    public String name;

    public MyThread3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread3 test  name:" + name+":"+ System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "MyThread3{" +
                "name='" + name + '\'' +
                '}';
    }
}
