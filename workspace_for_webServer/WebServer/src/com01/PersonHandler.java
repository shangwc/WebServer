package com01;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersonHandler extends DefaultHandler {
	private List<Person> persons;
	private Person person;
	private String tag;
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		persons = new ArrayList<Person>();		
	}
	@Override
	public void endDocument() throws SAXException {
		
		for(Person p:persons)
		{
			System.out.println(p);
		}
		// TODO Auto-generated method stub
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		this.tag = qName;
		if(tag.equals("person"))
		{
			person = new Person();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("person"))
		{
			persons.add(person);
		}
		this.tag = "";
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String contents = new String(ch,start,length).trim();
		if(null != tag )
		{
			if(tag.equals("name"))
			{
				person.setName(contents);
			}
			else if(tag.equals("age"))
			{
				if(contents.length()>0)
					person.setAge(Integer.valueOf(contents));
			}
		}
	}
	
	
	
}
