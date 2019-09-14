package com02;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLDeal {

	public static void main(String[] args) throws Exception, SAXException {
		// TODO Auto-generated method stub
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
		WebHandler handler = new WebHandler();
		parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(
				"web.xml")
				,handler );
		
		String input = "/reg";
		WebContext context = new WebContext(handler.getEntitys(),handler.getMappings());
		input = context.getCls(input);
		System.out.println(input);
		
		Class cls = Class.forName(input);
		Servlet servlet =(Servlet) cls.getConstructor().newInstance();
		servlet.service();
	}

}
