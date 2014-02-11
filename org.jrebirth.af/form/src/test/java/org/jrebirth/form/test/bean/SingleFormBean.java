package org.jrebirth.form.test.bean;

import org.jrebirth.form.annotation.Form;
import org.jrebirth.form.annotation.FormItem;

@Form
public class SingleFormBean {

	@FormItem
	private int size;
	
	@FormItem
	private double angle;
	
	@FormItem
	private String name;
}
