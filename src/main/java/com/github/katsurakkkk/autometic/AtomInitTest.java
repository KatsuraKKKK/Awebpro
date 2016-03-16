package com.github.katsurakkkk.autometic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class AtomInitTest {
	private final int THREAD_NUMBER = 10000;
	
//	private AtomicLong id = new AtomicLong(-1);
	private NotVolatieId id = new NotVolatieId(-1);
	
	private CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
	private ExecutorService exec = Executors.newFixedThreadPool(THREAD_NUMBER);
	
	class TestRunnable implements Runnable {

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
				if (tempId == 0 ) {
					while (true) {
						System.out.println(tempId);
						System.out.println("concurrency error###############################################################################");
					}
				}
				System.out.println("set AtomicLong");
				Random random = new Random();
				long newId = random.nextInt(10000) + 1;
//				id = new AtomicLong(newId);
				id = new NotVolatieId(newId);
			}
		}
	}
	
	public void multiThreadTest() {
		for (int i = 0; i < THREAD_NUMBER; i++) {
			exec.submit(new TestRunnable());
			System.out.println(i);
			latch.countDown();
		}
	}
	
	public static void main(String[] args) {
		AtomInitTest ait = new AtomInitTest();
		ait.multiThreadTest();
	}

}
