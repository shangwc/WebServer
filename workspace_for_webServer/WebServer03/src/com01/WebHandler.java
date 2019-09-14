package com01;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler {
	private List<Entity> entitys;
	private Entity entity;
	
	@Override
	public String toString() {
		return "WebHandler [entitys=" + entitys + ", entity=" + entity + ", mappings=" + mappings + ", mapping="
				+ mapping + ", tag=" + tag + ", clsname=" + clsname + "]";
	}

	private List<Mapping> mappings;
	private Mapping mapping;
	
	public List<Entity> getEntitys() {
		return entitys;
	}
	public void setEntitys(List<Entity> entitys) {
		this.entitys = entitys;
	}
	public List<Mapping> getMappings() {
		return mappings;
	}
	public void setMappings(List<Mapping> mappings) {
		this.mappings = mappings;
	}

	private String tag;
	private String clsname;
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		entitys = new ArrayList<Entity>();	
		mappings = new ArrayList<Mapping>();
		
	}
	@Override
	public void endDocument() throws SAXException {
		
		for(Entity p:entitys)
		{
			System.out.println(p);
		}
		
		for(Mapping m : mappings)
		{
			System.out.println(m);
		}
		// TODO Auto-generated method stub
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		this.tag = qName;
		
//		System.out.println("->"+qName);
		
		if(tag.equals("servlet"))
		{
			entity = new Entity();
			clsname = "servlet";
		}
		if(tag.equals("servlet-mapping"))
		{
			mapping = new Mapping();
			clsname = "servlet-mapping";
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("servlet"))
		{
			entitys.add(entity);
		}
		if(qName.equals("servlet-mapping"))
		{
			mappings.add(mapping);
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
//			System.out.println("->"+tag);
			if(clsname != null && clsname.equals("servlet"))
			{
				if(tag.equals("servlet-name"))
				{
//					System.out.println("->");
					entity.setName(contents);
				}
				else if(tag.equals("servlet-class"))
				{
					entity.setCls(contents);
				}
			}
			else if(clsname != null && clsname.equals("servlet-mapping"))
			{
				if(tag.equals("servlet-name"))
				{
					mapping.setName(contents);
				}
				else if(tag.equals("url-pattern"))
				{
					mapping.addPattern(contents);
				}
			}
		}
	}	
}
