package com.tarpitout.books.concurrencyinpratice;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Created by katsurakkkk on 8/18/16.
 */
public class ConcurrencyInPratice implements Thread.UncaughtExceptionHandler {
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
		FutureTask<String> future = new FutureTask<>(() -> {
			Thread.currentThread().sleep(3000);
			return "Hello Future.";
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

    /**
     * 对于unchecked异常,可以实现UncaughtExceptionHandler接口,当一个线程由于未捕获异常而退出时,JVM会把这个事件告报给应用程序提供的
     * UncaughtExceptionHandler,如果没有提供任何异常处理器,那么默认的行为是将栈追踪信息输出到System.error
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }

    /**
     * 当关闭线程池时保存被取消的任务
     */
	class TrackingExecutor extends AbstractExecutorService {
        private final ExecutorService exec;
        private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

        public TrackingExecutor() {
            this.exec = Executors.newCachedThreadPool();
        }

        public List<Runnable> getCancelledTasks() {
            if (!exec.isTerminated()) {
                throw new IllegalStateException("...");
            }
            return new ArrayList<Runnable>(tasksCancelledAtShutdown);
        }

        @Override
        public void shutdown() {

        }

        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void execute(Runnable command) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        if (isShutdown() && Thread.currentThread().isInterrupted()) {
                            tasksCancelledAtShutdown.add(command);
                        }
                    }
                }
            });
        }
    }

    /**
     * 典型线程池工作者线程结构
     */
    public void run() {
        Throwable thrown = null;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Run task...
            }
        } catch (Throwable e) {
            thrown = e;
        } finally {
            // Exited the thread...
        }
    }


	/**
	 * tryLock() 如果能够获得锁即获得锁并返回true,否则返回false
	 */
	public void tryLock() {
		final int[] data = {0};
		ReentrantLock lock1 = new ReentrantLock();
		ReentrantLock lock2 = new ReentrantLock();

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean tag = false;
				for(int i = 0; i < 10; i++) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					while (true) {
						if (lock1.tryLock()) {
							try {
								if (lock2.tryLock()) {
									try {
										data[0]++;
										System.out.println("++");
										tag = true;
									} finally {
										lock2.unlock();
									}
								}
							} finally {
								lock1.unlock();
							}
						}

						if (tag) {
							tag = false;
							break;
						}
					}
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean tag = false;
				for(int i = 0; i < 10; i++) {
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					while (true) {
						if (lock2.tryLock()) {
							try {
								if (lock1.tryLock()) {
									try {
										data[0]--;
										System.out.println("--");
										tag = true;
									} finally {
										lock1.unlock();
									}
								}
							} finally {
								lock2.unlock();
							}
						}

						if (tag) {
							tag = false;
							break;
						}
					}
				}
			}
		});

		thread1.start();
		thread2.start();

		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(data[0]);
	}


	/**
	 * Condition
	 */
	class ConditionBoundeBuffer<T> {
		protected final Lock lock = new ReentrantLock();

		private final Condition notFull = lock.newCondition();
		private final Condition notEmpty = lock.newCondition();

		private final T[] items = (T[]) new Object[3];
		private int tail, head, count;

		public void put(T x) throws InterruptedException {
			lock.lock();
			try {
				while (count == items.length) {
					notEmpty.await();
				}
				items[tail] = x;
				if (++tail == items.length)
					tail = 0;
				++count;
				notEmpty.signal();
			} finally {
				lock.unlock();
			}
		}

		public T take() throws InterruptedException {
			lock.lock();
			try {
				while (count == 0) {
					notEmpty.await();
				}
				T x = items[head];
				items[head] = null;
				if (++head == items.length) {
					head = 0;
				}
				--count;
				notFull.signal();
				return x;
			} finally {
				lock.unlock();
			}
		}
	}
}
