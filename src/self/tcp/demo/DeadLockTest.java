package self.tcp.demo;

import java.util.HashMap;
import java.util.concurrent.*;

public class DeadLockTest {

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();



    public static void main(String[] args) {

       Object a = new Object();
       Object b = a;
       System.out.println(b == a);
       Object c = new Object();
       a = c;
       System.out.println(b != null);
       System.out.println(a == b);




//        System.out.println(1 << 0);
//        DeadLockTest deadLockTest = new DeadLockTest();
//        deadLockTest.lockTest();
    }

    public void lockTest(){
        synchronized (DeadLockTest.class) {
            System.out.println("A");

            Future future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    synchronized (DeadLockTest.class){
                        System.out.println("B");
                    }
                }
            });
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

}
