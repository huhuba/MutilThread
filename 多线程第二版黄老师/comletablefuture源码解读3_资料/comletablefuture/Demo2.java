package org.com.msb.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author hj
 * @version 1.0
 * @description:
 * 注意：使用以下操作时，确保技术扎实且基础掌握牢固，对CompletableFuture和FJP有深刻了解
 * 否则出现任何问题，概不负责
 * @date 2021/6/9 19:55
 */
public class Demo2 {
    private static final String USER_MSG_FORMAT = "用户信息%d";
    private static final String USER_MSG_START_FORMAT = "正在获取用户%d的信息";
    private static final String USER_MSG_END_FORMAT = "获取结束";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread() + "\t" + "业务开始处理");
        CompletableFuture<List<CompletableFuture<Void>>> completableFutureList =
                CompletableFuture.supplyAsync(Demo2::getUserIdList)
                        .thenApplyAsync(userIdList -> userIdList.stream()
                                .map(userId -> CompletableFuture.supplyAsync(() -> getUserMsg(userId))
                                        .thenAccept(msg -> System.out.println(msg)))
                                .collect(Collectors.toList())
                        );
        mainThreadDo();
        completableFutureList.thenAccept(completableFutures -> {
            CompletableFuture<Void> completableFuture = CompletableFuture
                    .allOf(completableFutures.toArray(new CompletableFuture[0]));
            try {
                completableFuture.thenRun(() -> mainThreadDo()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).get();

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
