package org.laoguo.chapter1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.laoguo.util.PrintUtil.*;

class WorkThread implements Runnable {

	private Socket socket;
	private byte[] buf;

	public WorkThread(Socket socket) {
		this.socket = socket;
		buf = new byte[4096];
	}

	public void run() {
		long threadId = Thread.currentThread().getId();
		try {
			InputStream is = new BufferedInputStream(socket.getInputStream());
			OutputStream os = socket.getOutputStream();
			while (true) {
				int len = is.read(buf);
				if (len < 0) {
					PLN("thread recv len = 0: " + threadId);
					break;
				}
				PLN("read bytes:");
				//PLN(DS(buf, 0, len, 0x10));
				os.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				PLN("thread exit: " + threadId);
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8888);
			while (true) {
				PLN("accept...");
				try {
					Socket socket = serverSocket.accept();
					new Thread(new WorkThread(socket)).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
