package org.laoguo.thread;

import java.util.ArrayList;

import org.laoguo.util.Util;

class MyThread implements Runnable {

	int i = 0;
	
	Object lock = new Object();

	@Override
	public void run() {
			int a = i;
			a++;
			Thread.yield();
			i = a;
	}
}

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MyThread t = new MyThread();
		ArrayList<Thread> list = new ArrayList<Thread>();

		for (int i = 0; i < 4000; ++i) {
			Thread th = new Thread(t);
			list.add(th);
			th.start();
		}
		for(Thread th : list){
			th.join();
		}
		Util.P(t.i);
		Util.P("begin");
		Util.P("end");
	}

}
