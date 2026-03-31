/**
 * The module <strong>Workbench ShowCase</strong>.
 * 
 * @author Sébastien Bordes
 */
open module org.jrebirth.af.showcase.workbench {
	
	exports org.jrebirth.af.demo.workbench;
	exports org.jrebirth.af.demo.workbench.ui;
	exports org.jrebirth.af.demo.workbench.resources;

	provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.showcase.workbench.WorkbenchModuleStarter;

	requires javafx.base;
	requires javafx.graphics;
	
	requires org.jrebirth.af.component;
	requires org.jrebirth.af.core;
}
