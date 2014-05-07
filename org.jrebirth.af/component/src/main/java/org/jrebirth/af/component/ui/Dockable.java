package org.jrebirth.af.component.ui;

import javafx.scene.image.Image;

import org.jrebirth.af.core.resource.provided.JRebirthImages;
import org.jrebirth.af.core.ui.Model;

public interface Dockable extends Model {
	
	
	default String modelName(){
		return this.getClass().getSimpleName().replaceAll("Model", "");
	}
	
	default Image modelIcon(){
		return JRebirthImages.NOT_AVAILABLE.get();
	}
	

}
