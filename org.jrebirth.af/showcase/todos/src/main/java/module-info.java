/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
open module org.jrebirth.af.showcase.todos {

    exports org.jrebirth.af.showcase.todos;
    exports org.jrebirth.af.showcase.todos.ui.main;
    exports org.jrebirth.af.showcase.todos.ui.content;
    exports org.jrebirth.af.showcase.todos.ui.content.list;
    exports org.jrebirth.af.showcase.todos.ui.status;
    exports org.jrebirth.af.showcase.todos.ui.header;
    exports org.jrebirth.af.showcase.todos.ui.content.table;
    exports org.jrebirth.af.showcase.todos.service;
    
    //opens org.jrebirth.af.showcase.todos.ui.header to org.jrebirth.af.core;
    //opens org.jrebirth.af.showcase.todos.ui.main to org.jrebirth.af.core;
    //opens org.jrebirth.af.showcase.todos.ui.content.table to org.jrebirth.af.core;
    //opens org.jrebirth.af.showcase.todos.ui.content.list to org.jrebirth.af.core;
    
    //opens org.jrebirth.af.showcase.todos.images to org.jrebirth.af.core;
    
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires org.jrebirth.af.core;
    
    requires org.jrebirth.af.iconfont.bridge.fontawesome;
    
    uses org.jrebirth.af.api.module.ModuleStarter;
    provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.showcase.todos.TodosModuleStarter;

    requires org.slf4j;
}
