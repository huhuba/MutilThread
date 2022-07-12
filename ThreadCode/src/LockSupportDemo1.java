import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LockSupportDemo1 {
   volatile List lists=new ArrayList<>();
    public  void add (Object o){
        lists.add(o);
    }
    public  int size(){
        return  lists.size();
    }

    public static void main(String[] args) {
        LockSupportDemo1 demo1 = new LockSupportDemo1();
        new  Thread(()->{
            for(int i=0;i<10;i++){
                demo1.add(new Object());
                System.out.println("add:"+i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t1").start();

        new  Thread(()->{
            while (true){
                System.out.println("size:"+demo1.size());
                if(demo1.size()==5){

                    break;
                }
            }
            System.out.println("线程 t2 结束");
        },"t2").start();

    }
}
