/**
 * The JRebirth Core module.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.core {

    requires transitive javafx.controls;

    requires transitive org.jrebirth.af.api;
    
    requires static org.jrebirth.af.preloader;
    requires static org.jrebirth.af.resources;

    requires static ch.qos.logback.classic;
    requires static ch.qos.logback.core;

    requires io.github.classgraph;

    uses org.jrebirth.af.api.module.ModuleStarter;
    provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.core.module.CoreStarter;
    
    exports org.jrebirth.af.core.module;
    exports org.jrebirth.af.core.command.basic.stage;
    exports org.jrebirth.af.core.exception;
    exports org.jrebirth.af.core.exception.handler;
    exports org.jrebirth.af.core.command.single.internal;
    exports org.jrebirth.af.core.command.single.ui;
    exports org.jrebirth.af.core.resource.fxml;
    exports org.jrebirth.af.core.command.multi;
    exports org.jrebirth.af.core.link;
    exports org.jrebirth.af.core.facade;
    exports org.jrebirth.af.core.wave;
    exports org.jrebirth.af.core.command.ref;
    exports org.jrebirth.af.core.ui.object;
    exports org.jrebirth.af.core.ui.fxml;
    exports org.jrebirth.af.core.util;
    exports org.jrebirth.af.core.resource.i18n;
    exports org.jrebirth.af.core.resource.image;
    exports org.jrebirth.af.core.ui.adapter;
    exports org.jrebirth.af.core.resource.builder;
    exports org.jrebirth.af.core.resource.provided;
    exports org.jrebirth.af.core.log;
    exports org.jrebirth.af.core.service.basic.impl;
    exports org.jrebirth.af.core.resource;
    exports org.jrebirth.af.core.command.basic.showmodel;
    exports org.jrebirth.af.core.ui.handler;
    exports org.jrebirth.af.core.service.basic;
    exports org.jrebirth.af.core.command.single.pool;
    exports org.jrebirth.af.core.resource.provided.parameter;
    exports org.jrebirth.af.core.component.basic;
    exports org.jrebirth.af.core.resource.color;
    exports org.jrebirth.af.core.application;
    exports org.jrebirth.af.core.key;
    exports org.jrebirth.af.core.command.single;
    exports org.jrebirth.af.core.resource.parameter;
    exports org.jrebirth.af.core.resource.style;
    exports org.jrebirth.af.core.ui.simple;
    exports org.jrebirth.af.core.component.factory;
    exports org.jrebirth.af.core.command;
    exports org.jrebirth.af.core.concurrent;
    exports org.jrebirth.af.core.command.basic;
    exports org.jrebirth.af.core.component.behavior;
    exports org.jrebirth.af.core.wave.checker;
    exports org.jrebirth.af.core.ui;
    exports org.jrebirth.af.core.resource.font;
    exports org.jrebirth.af.core.service;
    
}
