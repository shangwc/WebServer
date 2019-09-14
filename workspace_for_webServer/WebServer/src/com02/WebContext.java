package com02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {

	private List<Entity> entitys;
	private List<Mapping> mappings;
	
	private Map<String,String> entityMap = new HashMap<String,String>();
	private Map<String,String> mappingMap = new HashMap<String,String>();

	public WebContext(List<Entity> entitys,List<Mapping> mappings)
	{
		this.entitys = entitys;
		this.mappings = mappings;
		for(Entity e:entitys)
		{
			entityMap.put(e.getName(), e.getCls());
		}
		for(Mapping m:mappings)
		{
			for(String str : m.getPatterns())
				mappingMap.put(str, m.getName());
		}
	}
	
	public String getCls(String url)
	{
		String test = mappingMap.get(url);
		return entityMap.get(test);
	}
}

