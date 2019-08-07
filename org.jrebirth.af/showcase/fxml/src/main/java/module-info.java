/**
 * The Module <strong>FXML Showcase</strong>.
 * 
 * @author Sébastien Bordes
 */
open module org.jrebirth.af.showcase.fxml {

	exports org.jrebirth.af.showcase.fxml;
	exports org.jrebirth.af.showcase.fxml.ui.main;
	exports org.jrebirth.af.showcase.fxml.ui.embedded;
	exports org.jrebirth.af.showcase.fxml.ui.hybrid;
	exports org.jrebirth.af.showcase.fxml.ui.included;
	exports org.jrebirth.af.showcase.fxml.ui.standalone;

	provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.showcase.fxml.FxmlModuleStarter;

// opens org.jrebirth.af.showcase.fxml.ui.main;
// opens org.jrebirth.af.showcase.fxml.ui.embedded ;
// opens org.jrebirth.af.showcase.fxml.ui.hybrid ;
// opens org.jrebirth.af.showcase.fxml.ui.included ;
// opens org.jrebirth.af.showcase.fxml.ui.standalone ;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	requires org.jrebirth.af.core;
	requires org.jrebirth.af.component;

	requires org.slf4j;

}
