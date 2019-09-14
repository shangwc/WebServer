package com01;

public class OthersServlet implements Servlet {
	public void service(Request request,Response response) {
		// TODO Auto-generated method stub
		System.out.println("OthersServlet");
		response.println("<html>");
		response.println("<head>");
		response.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\"/>");
		response.println("<title>");
		response.println("<ERROR>");
		response.println("</title>");
		response.println("</head>");
		response.println("<body>");
		response.println("ERROR");
		response.println("</body>");
		response.print("</html>");	
	}
}
