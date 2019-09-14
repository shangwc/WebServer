package com01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server04 {

	ServerSocket serverSocket;
	public static void main(String[] args) {
		Server04 server = new Server04();
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
			Socket client = serverSocket.accept();
			System.out.println("a client is connecting!!!");			
			System.out.println("_________________");
			//request
			Request request = new Request(client);
			//response
			
			
			Response  response = new Response(client);
			response.println("<html>");
			response.println("<head>");
			response.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\"/>");
			response.println("<title>");
			response.println("<服务器响应成功>");
			response.println("</title>");
			response.println("</head>");
			response.println("<body>");
			response.println("终于回来了");
			response.println("</body>");
			response.print("</html>");	
			
			response.pushToBrower(200);
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("a client is failed");
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
