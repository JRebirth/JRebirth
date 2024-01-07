/**
 * The module <strong>Font Icon ShowCase</strong>.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.fonticon {

    exports org.jrebirth.af.showcase.fonticon;
    exports org.jrebirth.af.showcase.fonticon.ui.fonticon;
    exports org.jrebirth.af.showcase.fonticon.ui.main;

    requires org.jrebirth.af.component;
    requires org.jrebirth.af.iconfontbridge.icons525;
    requires org.jrebirth.af.iconfontbridge.emojione;
    requires org.jrebirth.af.iconfontbridge.typicons;
    requires org.jrebirth.af.iconfontbridge.weathericons;
    requires org.jrebirth.af.iconfontbridge.fontawesome ;
    
	provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.showcase.fonticon.FonticonModuleStarter;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;

}
