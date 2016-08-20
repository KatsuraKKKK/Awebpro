package com.github.katsurakkkk.concurrencyinpratice;

import org.junit.*;

import java.util.concurrent.*;

/**
 * Created by katsurakkkk on 8/18/16.
 */
public class ConcurrencyInPraticeTest {
	private ConcurrencyInPratice cip;

	@Before
	public void init() {
		cip = new ConcurrencyInPratice();
	}

	@Test
	@Ignore
	public void testHello() {
		cip.hello();
	}

	@Test
	public void testCountDownLatch() {
		try {
			System.out.println(cip.countDownLatch());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFutureTask() {
		try {
			System.out.println(cip.futureTask());
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSemphore() {
		cip.semaphore();
	}

	@Test
	public void testCyclicBarrier() {
		try {
			System.out.println(cip.cyclicBarrier());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestCompletionService() {
		cip.completionService();
	}
}
