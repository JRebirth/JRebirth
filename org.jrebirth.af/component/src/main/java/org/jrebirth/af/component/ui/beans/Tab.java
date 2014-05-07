package org.jrebirth.af.component.ui.beans;

import java.io.Serializable;

import javafx.scene.control.Button;

import org.jrebirth.af.component.workbench.ui.DockableModel;
import org.jrebirth.af.core.key.UniqueKey;

public class Tab implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137109665415403740L;
	
	private String name;
	
	private UniqueKey<? extends DockableModel> modelKey;
	
	
	public static Tab create(){
		return new Tab();
	}

	public String name() {
		return name;
	}

	public Tab name(String name) {
		this.name = name;
		return this;
	}

	public UniqueKey<? extends DockableModel> modelKey() {
		return modelKey;
	}

	public Tab modelKey(UniqueKey<? extends DockableModel> modelKey) {
		this.modelKey = modelKey;
		return this;
	}

}
