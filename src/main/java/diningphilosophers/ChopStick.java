package diningphilosophers;

import java.time.Duration;


public class ChopStick {

    private static int stickCount = 0;

    private boolean iAmFree = true;
    private final int myNumber;
    private long delayTimeOut;

    public ChopStick() {
        myNumber = ++stickCount;
        delayTimeOut = (myNumber);

    }

    synchronized public boolean take() throws InterruptedException {
        while (!iAmFree) {
            wait(delayTimeOut*1000); // Wait notify OR timeout 
            if( !iAmFree ) { // La baguette n'est toujours pas libre
                System.out.println("Stick " + myNumber + " is not Free");
                return false; 
            }
        }
        // assert iAmFree;
        iAmFree = false;
        System.out.println("Stick " + myNumber + " Taken");
        // Pas utile de faire notifyAll ici, personne n'attend qu'elle soit occupée
        return true;
    }

    synchronized public void release() {
        // assert !iAmFree;
        System.out.println("Stick " + myNumber + " Released");
        iAmFree = true;
        notifyAll(); // On prévient ceux qui attendent que la baguette soit libre
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
