package com01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Dispatcher implements Runnable{
	
	private Socket client;
	private Response response;
	private Request request;
	public Dispatcher(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;
		this.request = new Request(client);
		this.response =new Response(client);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("url->"+request.getUrl());
			System.out.println("end");
			if(request.getUrl() == null ||  request.getUrl().equals("/"))
			{
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("login.html");
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = "";
				while((line=br.readLine())!=null)
				{
					System.out.println(line);
					response.print(line);

				}
				response.pushToBrower(200);
				br.close();
				is.close();
			}
			Servlet servlet = WebApp.getServletfFromUrl(request.getUrl());
			if(servlet != null)
			{
				servlet.service(request, response);
				response.pushToBrower(200);
			}
			else
			{
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
				//InputStreamReader isr= new InputStreamReader(is);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = "";
				while((line=br.readLine())!=null)
				{
					System.out.println(line);
					response.print(line);

				}
				response.pushToBrower(200);
				br.close();
				is.close();
			}
			
			
			//request
//			String[] print = request.getParameterValues("uname");
//			if(print == null)
//			{
//				return;
//			}
//			else
//			{
//				for(String i:print)
//				{
//					System.out.println(i);
//				}
//				System.out.println(print.toString());
//			}
//
//			System.out.println("____________________________end");
//			//response
//
//			Response  response = new Response(client);
//			Servlet servlet = WebApp.getServletfFromUrl(request.getUrl());
//			
//			if(null!=servlet)
//			{
//				servlet.service(request, response);		
//				
//				response.pushToBrower(200);
//			}
//			else
//			{
//				Servlet servlet2 = new OthersServlet();
//				servlet2.service(request, response);
//				response.pushToBrower(404);
//			}
			
			release();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("a client is failed");
		}
	}

	private void release(){
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
