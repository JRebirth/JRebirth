package org.jrebirth.af.rest.annotation;

public @interface RestTarget {

	enum Protocol{
		http,
		https
	}
	
	String value();
	
	Protocol protocol();
	String address();
	int port();
	String path();
	
}
