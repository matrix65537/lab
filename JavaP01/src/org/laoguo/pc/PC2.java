package org.laoguo.pc;

import java.util.concurrent.locks.*;

class Queue2 {
	private boolean full = false;
	private int v;

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}
}

class TaskP2 implements Runnable {

	private static int v = 0;
	private Queue q;
	private Lock lock;
	private Condition condP;
	private Condition condC;

	public TaskP2(Queue q, Lock lock, Condition condP, Condition condC) {
		this.q = q;
		this.lock = lock;
		this.condP = condP;
		this.condC = condC;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			lock.lock();
			while (q.isFull()) {
				try {
					System.out.println(name + " wait...");
					condP.await();
					System.out.println(name + " wait finish");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			v++;
			q.setV(v);
			System.out.println(name + " set: " + v);
			q.setFull(true);

			condC.signal();

			lock.unlock();
		}
	}

}

class TaskC2 implements Runnable {

	private Queue q;
	private Lock lock;
	private Condition condP;
	private Condition condC;

	public TaskC2(Queue q, Lock lock, Condition condP, Condition condC) {
		this.q = q;
		this.lock = lock;
		this.condP = condP;
		this.condC = condC;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			// Îª¿ÕµÄÊ±ºò
			lock.lock();
			while (!q.isFull()) {
				try {
					System.out.println(name + " wait...");
					condC.await();
					System.out.println(name + " wait finish");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int v = q.getV();
			System.out.println(name + " get: " + v);
			q.setFull(false);
			condP.signal();
			lock.unlock();
		}
	}
}

public class PC2 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condP = lock.newCondition();
		Condition condC = lock.newCondition();

		Queue q = new Queue();
		TaskP2 threadP = new TaskP2(q, lock, condP, condC);
		TaskC2 threadC = new TaskC2(q, lock, condP, condC);

		for (int i = 0; i < 8; ++i) {
			Thread threadA = new Thread(threadP, "Producter" + i);
			threadA.start();
		}
		for (int i = 0; i < 8; ++i) {
			Thread threadB = new Thread(threadC, "        Consumer" + i);
			threadB.start();
		}
	}
}
