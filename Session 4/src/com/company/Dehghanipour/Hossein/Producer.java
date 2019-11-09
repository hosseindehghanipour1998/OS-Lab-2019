package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    int id = 0 ;
    public static ArrayList<Producer>  threadPool = new ArrayList<>() ;
    private static int idCounter = 0 ;
    private static int THREAD_POOL_NUMBERS ;
    public static void createThreadPool(int threadPoolSize){
        THREAD_POOL_NUMBERS = threadPoolSize ;
        for ( int i = 0 ; i < threadPoolSize ; i++){
            Producer.threadPool.add(new Producer(idCounter++)) ;
        }
    }

    public Producer(int id){
        this.id = id ;
    }

    @Override
    public void run() {
        super.run();
        try {
           // for(int i = 0 ; i < Main.loopCounter ; i++){
            //int food = (int)(ThreadLocalRandom.current().nextInt()) ;
            Random rnd = new Random() ;
            int food = rnd.nextInt(5) + 1 ;
            Main.locker.lock();
            Main.stackSize-- ;
            Main.locker.unlock();
            if( Main.stackSize >= 0 ){
                System.out.println("In Thread : " + id + " | Stack Size : " + Main.stackSize);
                Main.producerSemaphore.acquire();
                Main.locker.lock();
                Main.bowls.push(food);
                Main.locker.unlock();
                System.out.println("ID : " + this.id + " | PUSHED : " + food) ;
                Main.consumerSemaphore.release();

            }
            else {
                return ;
            }

            //}



        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
