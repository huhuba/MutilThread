package zss;

import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

public class Test02 {
    public static Thread outN = null;
    public static Thread outC = null;

    public static void main(String[] args) {
        int[] n = new int[26];
        char[] c = new char[26];
        char a = 'a';
        for (int i = 0; i < n.length; i++) {
            n[i] = i + 1;
            a = (char) ('a' + i);
            c[i] = Character.toUpperCase(a);
        }
        outC = new Thread(() -> {
            for (char C : c) {
                System.out.print(C);
                LockSupport.unpark(outN);
                LockSupport.park();
            }

        });
        outN = new Thread(() -> {
            for (int N : n) {
                LockSupport.park();
                System.out.print(N);
                LockSupport.unpark(outC);
            }
        });

        outC.start();
        outN.start();
    }
}


class  MyThead  extends   Thread{
    @Override
    public void run() {
        super.run();
    }
}