/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
open module org.jrebirth.af.showcase.analyzer {

    exports org.jrebirth.af.showcase.analyzer.ui.controls;
    exports org.jrebirth.af.showcase.analyzer.ui.editor.ball;
    exports org.jrebirth.af.showcase.analyzer.ui.editor;
    exports org.jrebirth.af.showcase.analyzer.service;
    exports org.jrebirth.af.showcase.analyzer.service.impl;
    exports org.jrebirth.af.showcase.analyzer.ui.editor.ball.facade;
    exports org.jrebirth.af.showcase.analyzer.command;
    exports org.jrebirth.af.showcase.analyzer.ui.workbench;
    exports org.jrebirth.af.showcase.analyzer.ui.editor.ball.facade.command;
    exports org.jrebirth.af.showcase.analyzer.ui.properties;
    exports org.jrebirth.af.showcase.analyzer;

    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;
}
