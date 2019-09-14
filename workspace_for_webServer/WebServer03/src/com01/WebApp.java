package com01;

import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class WebApp {

	private static WebContext webContext ;
	static
	{

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			WebHandler handler = new WebHandler();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(
					"web.xml")
					,handler );

			webContext = new WebContext(handler.getEntitys(), handler.getMappings()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static Servlet getServletfFromUrl(String url)
	{
		String className = webContext.getCls(url);
		System.out.println(className);
		Class clz;
		try {
			clz = Class.forName(className);
			Servlet servlet = (Servlet)clz.getConstructor().newInstance();
			return servlet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			
	}	
}
