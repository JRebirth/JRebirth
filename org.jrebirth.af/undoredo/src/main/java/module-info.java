/**
 * The class <strong>undoredo</strong>.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.undoredo {
    exports org.jrebirth.af.undoredo.command;
    exports org.jrebirth.af.undoredo.service;
    //exports org.jrebirth.af.undoredo;

    requires javafx.base;
    requires org.jrebirth.af.core;
}
