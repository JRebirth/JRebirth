/**
 * The class <strong>module-info</strong>.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.transition {
    exports org.jrebirth.af.transition.command.slicer;
    exports org.jrebirth.af.transition.slicer;

	requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;

    requires javafx.base;
    requires javafx.graphics;

}
