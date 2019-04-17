/**
 * The module <strong>Presentation</strong>.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.presentation {

    requires javafx.web;

	requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;

    requires javax.activation.api;
    requires java.xml.bind;

    requires org.slf4j;

    exports org.jrebirth.af.presentation.ui.base;
    exports org.jrebirth.af.presentation.ui.qanda;
    exports org.jrebirth.af.presentation.ui.splash;
    exports org.jrebirth.af.presentation.service;
    exports org.jrebirth.af.presentation.ui;
    exports org.jrebirth.af.presentation.ui.classic;
    exports org.jrebirth.af.presentation;
    exports org.jrebirth.af.presentation.ui.image;
    exports org.jrebirth.af.presentation.ui.slidemenu;
    exports org.jrebirth.af.presentation.resources;
    exports org.jrebirth.af.presentation.ui.stack;
    exports org.jrebirth.af.presentation.command;
    exports org.jrebirth.af.presentation.ui.template;
    exports org.jrebirth.af.presentation.model;
}
