/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.fxform {
    exports org.jrebirth.af.core.ui.fxform;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;

    requires org.slf4j;
    requires validation.api;
    requires core;
}
