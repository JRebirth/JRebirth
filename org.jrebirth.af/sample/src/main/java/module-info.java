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

    requires javafx.graphics;
	requires javafx.controls;

    requires org.jrebirth.af.core;

    requires org.slf4j;
}
