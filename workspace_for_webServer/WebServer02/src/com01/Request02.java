package com01;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request02 {
	private String requestInfo;
	
	private String method;
	
	private String url;
	
	private String queryStr;
	
	private Map<String,List<String>> paremeterMap = new HashMap<String,List<String>>();
	
	public Request02(Socket client) {
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
		converMap();
	}
	
	private void parseRequestInfo()
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
	
	private void converMap()
	{
		String[] keyValue = this.queryStr.split("&");
		for(String kvs : keyValue )
		{
			String[] kv = kvs.split("=");
			String key =kv[0];
			int flg = kv.length;
			System.out.println("flg="+flg);
			if(flg > 1)
			{
				String value = kv[1];
				if(!this.paremeterMap.containsKey(key))
				{
					this.paremeterMap.put(key,new ArrayList<String>());
				}
				paremeterMap.get(key).add(value);
			}
		}
	}
	

	public String[] getParameterValues(String key)
	{
		List<String> lString = this.paremeterMap.get(key);
		if(lString == null)
		{
			return null;
		}
		return lString.toArray(new String[0]);
	}
	public String getParameterValue(String key)
	{
		List<String> lString = this.paremeterMap.get(key);
		if(lString == null)
		{
			return null;
		}
		String[] strs = getParameterValues(key);
		return strs[0];
	}
	
	public String getMethod() {
		return method;
	}


	public String getUrl() {
		return url;
	}



	public String getQueryStr() {
		return queryStr;
	}

}
