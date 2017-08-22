package org.laoguo.pool;

import java.util.concurrent.TimeUnit;

class MyJob implements Runnable {
	private String name;

	public MyJob(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(this.name + ": " + i);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

public class Main {

	public static void main(String[] args) {
		ThreadPool<MyJob> threadPool = new DefaultThreadPool<MyJob>();
		for (int i = 0; i < 100; i++) {
			threadPool.execute(new MyJob("Thread" + i));
		}
		threadPool.shutdown();
	}
}
