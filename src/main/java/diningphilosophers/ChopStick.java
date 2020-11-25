package diningphilosophers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ChopStick {

    private static int stickCount = 0;

    private boolean iAmFree = true;
    private final int myNumber;
    
    private final Lock verrou = new ReentrantLock();

    
    public ChopStick() {
        myNumber = ++stickCount;
    }

    public boolean take(int DELAY) throws InterruptedException {
        /* Try to take during 1 second */
        if( verrou.tryLock(DELAY, TimeUnit.MILLISECONDS)) {
            System.out.println("Stick " + myNumber + " taken");
            return true;
        }
        return false;
    }

    public void release() {
        /* Unlock the stick */
        System.out.println("Stick " + myNumber + " released");
        verrou.unlock();
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
