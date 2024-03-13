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

    opens org.jrebirth.af.sample.styles;
    opens org.jrebirth.af.sample.fonts;

    opens org.jrebirth.af.sample.ui;

    requires javafx.graphics;
	requires javafx.controls;

    requires org.jrebirth.af.core;
    requires org.jrebirth.af.preloader;
    requires org.jrebirth.af.resources;

    requires org.slf4j;
}
