/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.sample {
    
    exports org.jrebirth.af.sample.ui;
    exports org.jrebirth.af.sample;
    exports org.jrebirth.af.sample.command;
    exports org.jrebirth.af.sample.resources;
    exports org.jrebirth.af.sample.service;

    opens org.jrebirth.af.sample.ui;
    
    requires org.jrebirth.af.core;
}
