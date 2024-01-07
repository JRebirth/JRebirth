/**
 * The JRebirth API module.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.api {

    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive org.slf4j;

    exports org.jrebirth.af.api.wave.checker;
    exports org.jrebirth.af.api.resource.font;
    exports org.jrebirth.af.api.resource.color;
    exports org.jrebirth.af.api.component.factory;
    exports org.jrebirth.af.api.exception;
    exports org.jrebirth.af.api.wave.annotation;
    exports org.jrebirth.af.api.facade;
    exports org.jrebirth.af.api.link;
    exports org.jrebirth.af.api.wave.contract;
    exports org.jrebirth.af.api.component.behavior;
    exports org.jrebirth.af.api.ui.object;
    exports org.jrebirth.af.api.resource;
    exports org.jrebirth.af.api.ui;
    exports org.jrebirth.af.api.command.ref;
    exports org.jrebirth.af.api.key;
    exports org.jrebirth.af.api.resource.fxml;
    exports org.jrebirth.af.api.ui.annotation.type;
    exports org.jrebirth.af.api.resource.image;
    exports org.jrebirth.af.api.resource.builder;
    exports org.jrebirth.af.api.resource.parameter;
    exports org.jrebirth.af.api.resource.style;
    exports org.jrebirth.af.api.application;
    exports org.jrebirth.af.api.module;
    exports org.jrebirth.af.api.ui.fxml;
    exports org.jrebirth.af.api.log;
    exports org.jrebirth.af.api.annotation;
    exports org.jrebirth.af.api.resource.i18n;
    exports org.jrebirth.af.api.wave;
    exports org.jrebirth.af.api.command;
    exports org.jrebirth.af.api.component.basic;
    exports org.jrebirth.af.api.concurrent;
    exports org.jrebirth.af.api.service;
    exports org.jrebirth.af.api.component.behavior.annotation;
    exports org.jrebirth.af.api.annotation.bean;
    exports org.jrebirth.af.api.ui.annotation;

}
