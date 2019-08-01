/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.analyzer {

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

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires org.jrebirth.af.core;
    
    opens org.jrebirth.af.showcase.analyzer.ui.workbench;

    requires org.slf4j;
}
