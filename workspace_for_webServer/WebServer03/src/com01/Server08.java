package com01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server08 {

	ServerSocket serverSocket;
	public static void main(String[] args) {
		Server08 server = new Server08();
		server.start();
	}

	private void start()
	{
		try {
			serverSocket = new ServerSocket(8888);
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("构建服务器失败！！！");
		}
	}

	private void receive()
	{
		try {
			while(true)
			{
				Socket client = serverSocket.accept();
				System.out.println("a client is connecting!!!");			
				System.out.println("_________________");
				new Thread(new Dispatcher(client)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	private void stop()
	{
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
