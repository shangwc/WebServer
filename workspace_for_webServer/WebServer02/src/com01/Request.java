package com01;

import java.io.IOException;
import java.net.Socket;

public class Request {
	private String requestInfo;
	
	private String method;
	
	private String url;
	
	private String queryStr;
	
	public Request(Socket client) {
		// TODO Auto-generated constructor stub
		byte[] b = new byte[1024*1024];
		try {
			int len = client.getInputStream().read(b);
			requestInfo = new String(b,0,len);
			System.out.println(requestInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}	
		parseRequestInfo();
	}
	
	public void parseRequestInfo()
	{
		System.out.println("1->");
		int w = requestInfo.indexOf("/");
		//System.out.println(w);
		this.method = requestInfo.substring(0, w).trim();
		System.out.println(method);
		
		int endURL = requestInfo.indexOf("HTTP");
		System.out.println(endURL);
		
		url = requestInfo.substring(w+1,endURL).trim();
		System.out.println(url);
		
		int url_where = url.indexOf("?");
		if(url_where>=0)
		{
			queryStr = url.substring(url_where+1).trim();
			url = url.substring(0,url_where);
		}
		System.out.println(queryStr);
		System.out.println(url);
		
		if(method.equalsIgnoreCase("post"))
		{
			System.out.println("post");
			int begin = requestInfo.lastIndexOf("\r\n");
			if(queryStr == null)
			{
				queryStr = requestInfo.substring(begin).trim();
			}
			else
			{
				if(!requestInfo.substring(begin).trim().equals(""))
				queryStr += ("&"+ requestInfo.substring(begin).trim());
			}
			//queryStr = queryStr==null ? "" : queryStr;
			System.out.println(queryStr);
			
		}
	}
	
	
}
