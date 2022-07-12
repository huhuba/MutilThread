package org.com.msb.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author hj
 * @version 1.0
 * @description: TODO
 * @date 2021/6/9 21:01
 */
public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 将任务包装并放入到线程池中执行
        CompletableFuture.runAsync(() -> commonAction("动作1")).get();

    }

    public static void commonAction(String actionName) {
        System.out.println(actionName + "\tstart");
        System.out.println(actionName + "\tend");
    }
}
