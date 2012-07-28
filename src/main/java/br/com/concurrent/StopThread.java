package br.com.concurrent;

import java.util.concurrent.TimeUnit;

public class StopThread {

	private static boolean stopRequested;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (!stopRequested) {
					i++;
				}
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}

	/*
	 * Bytecode otimizado gerada pela JVM
	 * 
	 * if (!stopRequested) {
	 * 	while (true) {
	 *   i++;
	 *  }
	 * }
	 */
}

class SynchronizedStopThread {
	
	private static boolean stopRequested;
	
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	
	private static synchronized boolean stopRequested() {
		return stopRequested;
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (!stopRequested()) {
					i++;
				}
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(1);
		requestStop();
	}
}

class VolatileStopThread {
	
	private static volatile boolean stopRequested;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (!stopRequested) {
					i++;
				}
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}
}