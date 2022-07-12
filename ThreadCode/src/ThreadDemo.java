import java.util.concurrent.*;

public class ThreadDemo {
    public static   class   ThreadTest extends Thread {
        @Override
        public void run() {
            System.out.println("? extends  Thread 新建一个线程！");
        }
    }
    public  static  class   RunnableTest implements Runnable{

        @Override
        public void run() {
            System.out.println("? implements   Runnable  新建一个线程！");

        }
    }

    public  static   class   CallableTest implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
                System.out.println("? implements callable ,重写   call()方法，返回值 ");
            return 1;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

/*   第一种新建线程的方法
//继承 Thread类，重写run()方法，没有返回值
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();*/
/*

        //第二中实现线程的方法
        RunnableTest runnableTest = new RunnableTest();
        new Thread(runnableTest,"实现  Runnable接口新建线程").start();
*/

        //第三种实现线程的方法
        // 实现 Callable类，重写call()方法，有返回值
        CallableTest callableTest = new CallableTest();
        FutureTask futureTask = new FutureTask<Integer>(callableTest);
        new Thread(futureTask,"有返回值的线程").start();
        Object o = futureTask.get();// 返回  call()方法的返回值
        System.out.println(o);



    }
}
