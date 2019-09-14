package com01;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLDeal {

	public static void main(String[] args) throws Exception, SAXException {
		// TODO Auto-generated method stub
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
		PersonHandler handler = new PersonHandler();
		parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("p.xml")
				,handler );
				
	}

}
