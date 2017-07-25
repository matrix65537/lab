package org.laoguo.thread;

import java.util.ArrayList;

import org.laoguo.util.Util;

class ThreadA implements Runnable{

	@Override
	public void run() {
		int sum = 0;
		for(int i = 0; i < 10000000; ++i){
			for(int j = 0; j < 10000000; ++j){
				sum += (i * i / 17);
			}
		}
		//Util.P(Thread.currentThread().getName());
	}
}

public class Thread1 {

	public static void main(String[] args) {
		ArrayList<Thread> list = new ArrayList<Thread>();
		ThreadA ta = new ThreadA();
		for(int i = 0; i < 8; ++i){
			Thread t = new Thread(ta);
			t.setDaemon(true);
			list.add(t);
			t.start();
		}
		
		for(Thread t : list){
			try {
				Util.P(t.getName() + "before join");
				t.join(1);
				Util.P(t.getName() + "after join");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Util.P("Over");
	}
}
