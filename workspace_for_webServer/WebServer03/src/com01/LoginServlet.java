package com01;

public class LoginServlet implements Servlet {

	@Override
	public void service(Request request,Response response) {
		// TODO Auto-generated method stub
		System.out.println("LoginServlet");
		response.println("<html>");
		response.println("<head>");
		response.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\"/>");
		response.println("<title>");
		response.println("<hello>");
		response.println("</title>");
		response.println("</head>");
		response.println("<body>");
		response.println("欢迎回来"+(request.getParameterValue("uname")==null? new String("") : request.getParameterValue("uname")));
		response.println("</body>");
		response.print("</html>");	
	}

}
