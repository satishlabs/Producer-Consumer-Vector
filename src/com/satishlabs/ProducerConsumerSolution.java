package com.satishlabs;

import java.util.Vector;

public class ProducerConsumerSolution {
	public static void main(String[] args) {
		Vector sharedQueue = new Vector<>();
		int size=4;
		Thread prodThread = new Thread(new Producer(sharedQueue,size),"Producer");
		Thread consThrad = new Thread(new Consumer(sharedQueue,size),"Consumer");
		prodThread.start();
		consThrad.start();
	}
}
