package zss;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {
    //线程池中过的线程执行完任务后，是怎么从队列中取新的任务执行的。
    public static void main(String[] args) {
        ArrayBlockingQueue qu = new ArrayBlockingQueue<>(3);
        ThreadPoolExecutor ex = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, qu);
        ex.execute(new MyThread2("1"));
        ex.execute(new MyThread2("2"));
        ex.execute(new MyThread2("3"));
        ex.execute(new MyThread2("4"));
        ex.execute(new MyThread2("5"));

        ex.execute(new MyThread2("6"));
        ex.execute(new MyThread2("7"));
        ex.execute(new MyThread2("8"));
        ex.execute(new MyThread2("9"));
        ex.execute(new MyThread2("10"));

        ex.execute(new MyThread2("11"));
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
    }
}
