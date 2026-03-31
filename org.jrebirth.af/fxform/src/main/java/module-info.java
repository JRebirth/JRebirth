/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.fxform {
    exports org.jrebirth.af.core.ui.fxform;

    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;

    requires validation.api;
    requires core;
}
