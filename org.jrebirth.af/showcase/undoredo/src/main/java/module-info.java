/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.undoredo {

    
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires org.jrebirth.af.core;
    requires org.jrebirth.af.undoredo;

    requires org.slf4j;
    
    exports org.jrebirth.af.showcase.undoredo;
    exports org.jrebirth.af.showcase.undoredo.ui;
    exports org.jrebirth.af.showcase.undoredo.command;
}
