package org.jrebirth.form.test.bean;

import org.jrebirth.form.annotation.Form;
import org.jrebirth.form.annotation.FormSection;

@Form
public class ComposedFormBean {

	@FormSection
	private SingleFormBean singleFormBean;
	
	@FormSection
	private SingleBisFormBean singleBisFormBean;
		
}
