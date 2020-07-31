package com.tarpitout.aio.jdk.autometic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomInitTest {
	private final int THREAD_NUMBER = 1000;
	
//	private AtomicLong id = new AtomicLong(-1);
	private volatile NotVolatieId id = new NotVolatieId(-1);
	
	private CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
	private ExecutorService exec = Executors.newFixedThreadPool(THREAD_NUMBER);
	
	class ProductorRunnable implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("wait to begin");
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			while (true) {
				System.out.println("set AtomicLong");
				Random random = new Random();
				long newId = random.nextInt(10000) + 1;
//				id = new AtomicLong(newId);
				id = new NotVolatieId(newId);
//			}
		}
	}
	
	class FreeProductorRunnable implements Runnable {

		@Override
		public void run() {
//			while (true) {
				System.out.println("set AtomicLong");
				Random random = new Random();
				long newId = random.nextInt(10000) + 1;
//				id = new AtomicLong(newId);
				id = new NotVolatieId(newId);
//			}
		}
	}
	
	class ComsumerRunnable implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("wait to begin");
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (true) {
				long tempId = id.get();
				System.out.println(tempId);
				if (tempId == 0 ) {
					while (true) {
						System.out.println("concurrency error###############################################################################");
					}
				}
			}
		}
	}
	
	public void multiThreadTest() {
		for (int i = 0; i < THREAD_NUMBER / 2; i++) {
			System.out.println(i);
			exec.submit(new ProductorRunnable());
			latch.countDown();
			exec.submit(new ComsumerRunnable());
			latch.countDown();
		}
		
		while (true) {
			exec.submit(new FreeProductorRunnable());
		}
	}
	
	public static void main(String[] args) {
		AtomInitTest ait = new AtomInitTest();
		ait.multiThreadTest();
	}

}
