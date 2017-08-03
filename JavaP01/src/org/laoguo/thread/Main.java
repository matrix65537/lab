package org.laoguo.thread;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;

class Monitor implements Runnable {

	private HashMap<Thread, State> threadMap;

	public Monitor() {
		threadMap = new HashMap<>();
	}

	public void register(Thread th) {
		threadMap.put(th, th.getState());
	}

	public void remove(Thread th) {
		threadMap.remove(th);
	}

	@Override
	public void run() {
		for (Map.Entry<Thread, State> entry : threadMap.entrySet()) {
			Thread th = entry.getKey();
			State state = entry.getValue();
			System.out.println(th.getName() + ": " + state);
		}
		while (true) {
			for (Map.Entry<Thread, State> entry : threadMap.entrySet()) {
				Thread th = entry.getKey();
				State state = entry.getValue();
				State newState = th.getState();
				if (state != newState) {
					System.out.println(th.getName() + ": " + newState);
					entry.setValue(newState);
				}
			}
		}
	}
}

class TWorker implements Runnable {

	@Override
	public void run() {
		try {
			//Thread.sleep(3000);
			System.out.println("before yield");
			while(this != null){
				Thread.yield();
			}
			System.out.println("after yield");
			double sum = 0;
			for(int i = 0; i < 10000; ++i){
				sum += (i * i * 17 + 65537 / 17);
			}
			System.out.println("sum end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TWorker worker = new TWorker();
		Thread threadWorker = new Thread(worker);

		Monitor monitor = new Monitor();
		Thread threadMonitor = new Thread(monitor);

		monitor.register(threadWorker);
		//monitor.register(Thread.currentThread());

		threadMonitor.start();
		threadWorker.start();
	}
}
