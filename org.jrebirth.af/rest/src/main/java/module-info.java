/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.rest {
    exports org.jrebirth.af.rest.service.impl;
    exports org.jrebirth.af.rest;
    exports org.jrebirth.af.rest.service;
    exports org.jrebirth.af.rest.annotation;

    requires java.ws.rs;
    requires javafx.base;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;
    requires org.slf4j;
}
