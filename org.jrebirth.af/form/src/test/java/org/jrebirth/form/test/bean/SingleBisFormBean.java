package org.jrebirth.form.test.bean;

import org.jrebirth.form.annotation.Form;
import org.jrebirth.form.annotation.FormItem;

@Form
public interface SingleBisFormBean {

	@FormItem
	int getSize();
	
	@FormItem
	double getAngle();
	
	@FormItem
	String getName();
}
