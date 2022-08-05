package zss;


public class TestThis {
    public static void main(String[] args) {
        System.out.println("main start:"+Thread.currentThread().getName());
        Thread t1 = new Thread(new MyThread(), "t1");
        Thread t2 = new Thread(new MyThread(), "t2");
        t1.start();
//        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main end:"+Thread.currentThread().getName());
    }
}

class MyThread  extends  Thread{
    @Override
    public void run() {

        System.out.println("Thread = " + Thread.currentThread().getName());
        synchronized (this){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" now   name:"+Thread.currentThread().getName());
        }
        System.out.println("this:" + Thread.currentThread().getName());
    }
}
