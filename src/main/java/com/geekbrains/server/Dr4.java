package com.geekbrains.server;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dr4 {
    public static final Object head = new Object();
    public static String curLetter = "A";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            try{
                for(int i = 0;i < 5;i++){
                    synchronized (head){
                        while(curLetter != "A"){
                            head.wait();
                        }
                        System.out.print("A");
                        curLetter = "B";
                        head.notifyAll();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try{
                for(int i = 0;i < 5;i++){
                    synchronized (head){
                        while(curLetter != "B"){
                            head.wait();
                        }
                        System.out.print("B");
                        curLetter = "C";
                        head.notifyAll();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try{
                for(int i = 0;i < 5;i++){
                    synchronized (head){
                        while(curLetter != "C"){
                            head.wait();
                        }
                        System.out.print("C");
                        curLetter = "A";
                        head.notifyAll();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }


}
