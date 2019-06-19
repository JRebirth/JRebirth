package org.jrebirth.af.showcase.diagram.service;

import java.util.HashMap;
import java.util.Map;

import org.jrebirth.af.core.service.DefaultService;

public class IncrementalNameService extends DefaultService {

    private Map<String, Integer> incrMap = new HashMap<>();
    
    
    public String getIncrementedName(String string) {
    	Integer nb = incrMap.get(string);
		if(nb == null) {
			incrMap.put(string, 1);
		}else {
			incrMap.put(string, ++nb);
		}
		return string + incrMap.get(string);
	}
}
