package org.laoguo.pc;

class Queue {
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

class TaskP implements Runnable {

	private static int v = 0;
	private Queue q;

	public TaskP(Queue q) {
		this.q = q;
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
			synchronized (q) {
				while (q.isFull()) {
					try {
						System.out.println(name + " wait...");
						q.wait();
						System.out.println(name + " wait finish");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				v++;
				q.setV(v);
				System.out.println(name + " set: " + v);
				q.setFull(true);
				q.notifyAll();
			}
		}
	}

}

class TaskC implements Runnable {

	private Queue q;

	public TaskC(Queue q) {
		this.q = q;
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
			synchronized (q) {
				// Îª¿ÕµÄÊ±ºò
				while (!q.isFull()) {
					try {
						System.out.println(name + " wait...");
						q.wait();
						System.out.println(name + " wait finish");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int v = q.getV();
				System.out.println(name + " get: " + v);
				q.setFull(false);
				q.notifyAll();
			}
		}
	}
}

public class PC {

	public static void main(String[] args) {
		Queue q = new Queue();
		TaskP threadP = new TaskP(q);
		TaskC threadC = new TaskC(q);

		for(int i = 0; i < 4; ++i){
			Thread threadA = new Thread(threadP, "Producter" + i);
			threadA.start();
		}
		for(int i = 0; i < 4; ++i){
			Thread threadB = new Thread(threadC, "        Consumer" + i);
			threadB.start();
		}
	}
}
