/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.processor {
    exports org.jrebirth.af.processor;

    requires transitive commons.lang3;
    requires transitive java.compiler;
    requires transitive maven.model;
    requires transitive plexus.utils;

    requires transitive org.jrebirth.af.api;
    requires transitive org.jrebirth.af.core;
    requires transitive org.jrebirth.af.tooling.codegen;

    requires roaster.api;
    requires roaster.jdt;

    provides javax.annotation.processing.Processor with org.jrebirth.af.processor.BeanProcessor, org.jrebirth.af.processor.ComponentProcessor;

}
