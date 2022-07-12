package org.com.msb.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hj
 * @version 1.0
 * @description: TODO
 * @date 2021/6/9 19:55
 */
public class Demo1 {
    private static final String USER_MSG_FORMAT = "用户信息%d";
    private static final String USER_MSG_START_FORMAT = "正在获取用户%d的信息";
    private static final String USER_MSG_END_FORMAT = "获取结束";
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread() + "\t" + "业务开始处理");
        Future<List<Integer>> userIdListFuture = threadPoolExecutor.submit(Demo1::getUserIdList);
        mainThreadDo();
        List<Integer> userIdList = userIdListFuture.get();
        List<Future<String>> userMsgFutureList = new ArrayList<>();
        userIdList.forEach(userId -> userMsgFutureList.add(threadPoolExecutor.submit(() -> getUserMsg(userId))));
        mainThreadDo();
        // 等待所有获取用户信息的操作执行完毕
        for (int i = 0; i < userMsgFutureList.size(); i++) {
            String userMsg = userMsgFutureList.get(i).get();
            System.out.println(userMsg);
        }
        mainThreadDo();
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(Thread.currentThread() + "\t" + "业务处理完毕");
    }


    public static void mainThreadDo() {
        System.out.println(Thread.currentThread() + "\t" + "主线程开始执行别的逻辑");
        sleep();
        System.out.println(Thread.currentThread() + "\t" + "主线程结束执行别的逻辑");
    }

    public static String getUserMsg(Integer userId) {
        System.out.println(String.format(Thread.currentThread() + "\t" + USER_MSG_START_FORMAT, userId));
        sleep();
        System.out.println(Thread.currentThread() + "\t" + USER_MSG_END_FORMAT);
        return String.format(USER_MSG_FORMAT, userId);
    }


    // 查询数据库并返回所有用户ID列表
    public static List<Integer> getUserIdList() {
        sleep();
        return new ArrayList<Integer>() {
            {
                this.add(1);
                this.add(2);
                this.add(3);
                this.add(4);
                this.add(5);
            }
        };
    }

    // 为了模拟数据库延时操作
    public static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
