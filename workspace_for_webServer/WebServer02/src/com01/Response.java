package com01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
	
	private StringBuilder headInfo = new StringBuilder(); 
	private StringBuilder content = new StringBuilder();
	private int len = 0;
	private BufferedWriter bw;
	private String blank = " ";
	private String CRLF = "\r\n";
	
	public Response(Socket client) {
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("构造response失败！！！");
		}
	}
	
	private void creatHeadInfo(int code)
	{
		/*
		 * 返回
		 *1、响应行
		 *		HTTP/1.1 200 OK 
		 */
		headInfo.append("HTTP/1.1").append(blank);
		headInfo.append(code).append(" ");
		headInfo.append("OK").append(CRLF);
					
		/*
		 *2、响应头 （最后一行存在空行）
		 *		
		 *
		 */
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
		headInfo.append("Content-type:text/html").append(CRLF);
		headInfo.append("Content-length:").append(len).append(CRLF);
		headInfo.append(CRLF);
	}
	
	public Response print(String str)
	{
		content.append(str);
		len+=str.getBytes().length;
		return this;
	}
	
	public Response println(String str)
	{
		content.append(str).append(CRLF);
		len+=(str+CRLF).getBytes().length;
		return this;
	}
	
	public void pushToBrower(int code)
	{
		
		try {
			creatHeadInfo(200);
			bw.write(headInfo.toString());
			bw.write(content.toString());
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
