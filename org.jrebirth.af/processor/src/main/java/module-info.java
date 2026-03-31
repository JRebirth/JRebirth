/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.processor {
    exports org.jrebirth.af.processor;

    requires commons.lang3;
    requires transitive java.compiler;
    requires maven.model;
    requires plexus.utils;

    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;
    requires roaster.api;
    //requires roaster.jdt;
    requires org.jrebirth.af.tooling.codegen;

    provides javax.annotation.processing.Processor with org.jrebirth.af.processor.BeanProcessor, org.jrebirth.af.processor.ComponentProcessor;

}
