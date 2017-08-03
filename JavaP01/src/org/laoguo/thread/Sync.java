package org.laoguo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Node {
	Object obj = new Object();
	private Lock lock = new ReentrantLock();

	public synchronized void f1() throws Exception {
		System.out.println(Thread.currentThread().getName());
		throw new Exception();
	}
	
	public void f2() throws Exception {
		synchronized(obj){
			System.out.println(Thread.currentThread().getName());
			throw new Exception();
		}
	}
	
	public void f3() throws Exception {
		lock.lock();
		System.out.println(Thread.currentThread().getName());
		if(obj != null){
			throw new Exception();
		}
		lock.unlock();
	}
}

class Worker implements Runnable {
	
	private Node node; 
	
	public Worker(Node node){
		this.node = node;
	}

	@Override
	public void run() {
		try {
			node.f3();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Sync {

	public static void main(String[] args) {
		Node node = new Node();
		new Thread(new Worker(node)).start();
		new Thread(new Worker(node)).start();
	}
}
