package zss;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledThreadPoolExecutor {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(1);
/*        ex.execute(new MyRunnable("1"));
        ex.schedule(new MyRunnable("2"), 1, TimeUnit.SECONDS);*/
        //延时,周期,的实现本质是,循环入队列.
        ex.scheduleAtFixedRate(new MyRunnable("3"), 1, 2, TimeUnit.SECONDS);
        ex.scheduleWithFixedDelay(new MyRunnable("4"), 1, 2, TimeUnit.SECONDS);
//        ex.shutdown();

    }
}

class MyRunnable implements Runnable {
    public String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("toString() = " + toString());
    }

    @Override
    public String toString() {
        return "MyRunnable{" +
                "name='" + name + '\'' +
                '}';
    }
}
