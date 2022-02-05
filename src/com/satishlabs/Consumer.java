package com.satishlabs;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {
	private final Vector sharedQueue;
	private final int SIZE;
	
	
	
	public Consumer(Vector sharedQueue, int sIZE) {
		super();
		this.sharedQueue = sharedQueue;
		SIZE = sIZE;
	}



	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("Consumed: "+consume());
				Thread.sleep(100);
			}catch (InterruptedException e) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE,null,e);
			}
		}
	}



	private int consume() throws InterruptedException {
		while(sharedQueue.isEmpty()) {
			synchronized (sharedQueue) {
				System.out.println("The queue is empty "+Thread.currentThread().getName()+" is waiting , siz "+sharedQueue.size());
				sharedQueue.wait();
			}
		}
		synchronized (sharedQueue) {
			sharedQueue.notifyAll();
			return (int) sharedQueue.remove(0);
		}
		
	}

}
