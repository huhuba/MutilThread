package java.util;

import java.util.HashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Map synchronizedMap = Collections.synchronizedMap(new HashMap());
        ArrayList<Object> arrayList = new ArrayList<>();
        HashSet<Object> hashSet = new HashSet<>();
//        new BlockingDeque<>()
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    }
}
