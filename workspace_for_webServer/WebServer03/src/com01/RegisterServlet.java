package com01;

public class RegisterServlet implements Servlet {

	@Override
	public void service(Request request,Response response) {
		// TODO Auto-generated method stub
		System.out.println("RegisterServlet");
		response.print("success");
		
	}

}
