package com.clarkrao.springboot.test;

import java.util.concurrent.*;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/6 22:26
 * @Description: FutureTest
 */
public class FutureTest {

    public static void main(String[] args) {
//        testFuture();
        try {
            testCompletableFuture5();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试CompletableFuture
     */
    private static void testCompletableFuture() {
//        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
//
//        new Thread(() -> {
//            completableFuture.completeExceptionally(new RuntimeException("error"));
//            completableFuture.complete(Thread.currentThread().getName());
//        }).start();

        CompletableFuture<String> completableFuture = CompletableFuture
                                                            .supplyAsync(() -> Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            System.out.println(i+"---------------------------------");
        }

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void testCompletableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String> future1 = future.thenApply(i -> i + 5).thenApply(i -> String.valueOf(i));
        System.out.println(future1.get());
    }

    private static void testCompletableFuture3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);

        future.thenAccept(System.out::println);

    }

    private static void testCompletableFuture4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        System.out.println(future.thenAcceptBoth(CompletableFuture.supplyAsync(() -> 66),
                (x, y) -> System.out.println(x + y)).get());

    }

    private static void testCompletableFuture5() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        System.out.println(future.thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 1)).get());

    }

    /**
     * 测试Future
     */
    private static void testFuture() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(() -> Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            System.out.println(i+"---------------------------------");
        }
        try {
            String result = future.get();
            System.out.println("result: "+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
