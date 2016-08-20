package com.github.katsurakkkk.concurrencyinpratice;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by katsurakkkk on 8/18/16.
 */
public class ConcurrencyInPratice {
	public void hello() {
		System.out.println("Hello");
	}

	/**
	 * 闭锁
	 */
	public long countDownLatch() throws InterruptedException {
		int nThreads = 5;
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					endGate.countDown();
				}
			};
			t.start();
		}

		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}

	/**
	 * FutureTask
	 */
	public String futureTask() throws ExecutionException, InterruptedException {
		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.currentThread().sleep(3000);
				return "Hello Future.";
			}
		});

		Thread thread = new Thread(future);
		thread.start();
		System.out.println("future.get()");
		return future.get();
	}

	/**
	 * Semaphore
	 */
	public void semaphore() {
		Semaphore sem = new Semaphore(1);
		int nThread = 5;
		for (int i = 0; i < nThread; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						sem.acquire();
						int random = (int) (5 * Math.random());
						sleep(random);
						System.out.println(currentThread().getId());
						sem.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
	}

	/**
	 * 栅栏
	 * @return
	 * @throws InterruptedException
	 */
	public long cyclicBarrier() throws InterruptedException {
		int nThreads = 25;
		final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("Barrier pass...");
			}
		});

		long start = System.nanoTime();
		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						barrier.await();
						sleep(1000);
					} catch (BrokenBarrierException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long end = System.nanoTime();
		return end - start;
	}

	/**
	 * CompletionService
	 */
	public void completionService() {
		int nThread = 5;
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService  completionService = new ExecutorCompletionService(executor);

		for (int i = 0; i < nThread; i++) {
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					if (Thread.currentThread().isInterrupted()) {
						return "interrupted";
					}
					int st = (int)(Math.random() * 5000);
					Thread.sleep(st);
					return Thread.currentThread().getId() + ":" + st;
				}
			});
		}

		for (int i = 0; i < nThread; i++) {
			try {
				Future<String> f = completionService.take();
				System.out.println("Round" + i);
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 取消: 即使任务不响应中断,限时运行的方法仍能够返回到他的调用者。在任务启动以后偶timedRun执行一个限时的join方法,
	 * 在join返回后,将检查是否有异常抛出,有的话再次抛出异常,由于Throwable在两个线程之间共享,所以设置为volatile
	 */
	public void timeRun(final Runnable r) throws Throwable {
		ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(5);
		class  RethrowableTask implements Runnable {
			private volatile Throwable t;
			@Override
			public void run() {
				try {
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}
			void rethrow() throws Throwable {
				if (t != null) {
					throw t;
				}
			}
		}

		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExec.schedule(() -> taskThread.interrupt(), 100000, TimeUnit.MILLISECONDS);
		taskThread.join(10000);
		task.rethrow();
	}

	public void betterTimeRun(Runnable r) throws Throwable {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Future<?> task = executorService.submit(r);
		try {
			task.get(10000, TimeUnit.MILLISECONDS);
		} catch (TimeoutException e) {
			// 在finally中被取消
		} catch (ExecutionException e) {
			throw e.getCause();
		} finally {
			task.cancel(true);
		}
	}
}
