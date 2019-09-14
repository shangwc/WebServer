package com01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server02 {

	ServerSocket serverSocket;
	public static void main(String[] args) {
		Server02 server = new Server02();
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
			InputStream is = client.getInputStream();
			byte[] datas = new byte[1024*1024];
			int len = is.read(datas);
			String requestInfo = new String(datas,0,len);
			System.out.println(requestInfo);
			
			System.out.println("_________________");
			//response
			
			StringBuilder content = new StringBuilder();
			content.append("<html>");
			content.append("<head>");
			content.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\">");
			content.append("<title>");
			content.append("<服务器响应成功>");
			content.append("</title>");
			content.append("</head>");
			content.append("<body>");
			content.append("终于回来了");
			content.append("</body>");
			content.append("</html>");
			int size = content.toString().getBytes().length;
			StringBuilder responseInfo = new StringBuilder();
			
			
			String blank = " ";
			String CRLF = "\r\n";
			/*
			 * 返回
			 *1、响应行
			 *		HTTP/1.1 200 OK 
			 */
			responseInfo.append("HTTP/1.1").append(blank);
			responseInfo.append("200 ");
			responseInfo.append("OK").append(CRLF);
						
			/*
			 *2、响应头 （最后一行存在空行）
			 *		
			 *
			 */
//			responseInfo.append("Date:").append(new Date()).append(CRLF);
//			responseInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
			responseInfo.append("Content-type:text/html").append(CRLF);
			responseInfo.append("Content-length:").append(size).append(CRLF);
			responseInfo.append(CRLF);
			
			responseInfo.append(content.toString());
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bw.write(responseInfo.toString());
			bw.flush();
			
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
