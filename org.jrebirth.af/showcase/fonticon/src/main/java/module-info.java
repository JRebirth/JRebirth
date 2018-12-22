/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.fonticon {

    exports org.jrebirth.af.showcase.fonticon;
    exports org.jrebirth.af.showcase.fonticon.ui.fonticon;
    exports org.jrebirth.af.showcase.fonticon.ui.main;

    requires org.jrebirth.af.component;
    requires org.jrebirth.af.iconfont.bridge.icons525;
    requires org.jrebirth.af.iconfont.bridge.emojione;
    requires org.jrebirth.af.iconfont.bridge.typicons;
    requires org.jrebirth.af.iconfont.bridge.weathericons;
    requires org.jrebirth.af.iconfont.bridge.fontawesome;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;

}
