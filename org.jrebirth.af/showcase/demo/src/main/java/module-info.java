/**
 * The class <strong>module-info</strong>.
 * TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.demo {
    exports org.jrebirth.af.showcase.demo.ui;
    exports org.jrebirth.af.showcase.demo;
    exports org.jrebirth.af.showcase.demo.resources;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.component;
    requires org.jrebirth.af.core;
    requires org.jrebirth.af.showcase.workbench;
}
