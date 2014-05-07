package org.jrebirth.af.component.ui.beans;



public class TabConfig {

	private TabOrientation orientation = TabOrientation.top;
	
	public static TabConfig create(){
		return new TabConfig();
	}
	
	
	public TabOrientation orientation(){
		return orientation;
	}
	
	public TabConfig orientation(TabOrientation orientation){
		this.orientation = orientation;
		return this;
	}

}
