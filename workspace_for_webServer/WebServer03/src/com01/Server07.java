package com01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server07 {

	ServerSocket serverSocket;
	public static void main(String[] args) {
		Server07 server = new Server07();
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
			String[] print = request.getParameterValues("uname");
			if(print == null)
			{
				return;
			}
			else
			{
				for(String i:print)
				{
					System.out.println(i);
				}
				System.out.println(print.toString());
			}

			System.out.println("____________________________end");
			//response

			Response  response = new Response(client);
			Servlet servlet = WebApp.getServletfFromUrl(request.getUrl());
			
			if(null!=servlet)
			{
				servlet.service(request, response);		
				
				response.pushToBrower(200);
			}
			else
			{
				Servlet servlet2 = new OthersServlet();
				servlet2.service(request, response);
				response.pushToBrower(404);
			}
			

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
