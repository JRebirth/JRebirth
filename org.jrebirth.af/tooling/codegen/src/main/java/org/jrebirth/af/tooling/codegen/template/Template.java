package org.jrebirth.af.tooling.codegen.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

	private static final String NEW_LINE = "\r\n";
	
	public static final String regex = "(\\$\\w+\\b)|(\\$\\{[\\w-]+\\})"; 
	
	private Pattern p = Pattern.compile(regex);
	
	private String name;
	
	private String content = "";
	
	private Map<String, String> parameters = new HashMap<>();

	/**
	 * @param name
	 */
	public Template(String name) {
		super();
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String getContent() {
		return content;
	}

	public void addLine(String line) {
		this.content += line + NEW_LINE;
		parseLine(line);
	}

	private void parseLine(String line) {
		Matcher m = p.matcher(line);
		 while (m.find()){
			 
			 // Extract any template parameter name
			 String parameterName = m.group();
			 parameterName = parameterName.replaceAll("\\$|\\{|\\}", "");
			 
			 parameters.put(parameterName, null);
		 }
		
	}

	public List<String> getParameterNames(){
		
		return new ArrayList<String>(parameters.keySet());
	}
	
//	public String addParameterDefaultValue(String parameterName, String defaultValue){
//		
//		return parameters.put(parameterName, defaultValue);
//	}
//	
//	public String getParameterDefaultValue(String parameterName){
//		
//		return parameters.get(parameterName);
//	}

	public void clean() {
		if(content.endsWith(NEW_LINE)){
			content = content.substring(0, content.length()-NEW_LINE.length());
		}
	}
	
}
