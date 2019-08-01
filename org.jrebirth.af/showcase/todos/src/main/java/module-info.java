/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.fxml {

    exports org.jrebirth.af.showcase.todos;
    
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires org.jrebirth.af.core;
    
    requires org.jrebirth.af.iconfont.bridge.fontawesome;
    
    exports org.jrebirth.af.showcase.todos.ui.main;
    exports org.jrebirth.af.showcase.todos.ui.content;
    exports org.jrebirth.af.showcase.todos.ui.content.list;
    exports org.jrebirth.af.showcase.todos.ui.status;

    requires org.slf4j;
}
