package br.com.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Executor {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 20; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					System.out.println("Processando...");
					System.out.println("Tempo: " + (System.currentTimeMillis() - start) / 1000);
				}
			});
		}
		
		
		service.shutdown();
		service.awaitTermination(30, TimeUnit.SECONDS);
	}
}
