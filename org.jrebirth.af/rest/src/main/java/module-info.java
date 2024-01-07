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
    
    opens org.jrebirth.af.rest.service to org.jrebirth.af.core;
    opens org.jrebirth.af.rest.service.impl to org.jrebirth.af.core;

    requires java.ws.rs;
    requires javafx.base;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;
    requires org.slf4j;
    
    provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.rest.RestModuleStarter;
}
