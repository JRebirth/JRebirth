/**
 * The JRebirth Component module.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.component {
    exports org.jrebirth.af.component.ui.tab;
    exports org.jrebirth.af.component.behavior.dockable.data;
    exports org.jrebirth.af.component.behavior.dockable;
    exports org.jrebirth.af.component.behavior.swipable.data;
    exports org.jrebirth.af.component.ui;
    exports org.jrebirth.af.component.behavior.swipable;
    exports org.jrebirth.af.component.behavior.swipable.impl;
    exports org.jrebirth.af.component.command.tab;
    exports org.jrebirth.af.component.command.dock;
    exports org.jrebirth.af.component.ui.workbench;
    exports org.jrebirth.af.component.ui.stack;
    exports org.jrebirth.af.component.ui.dock;
    exports org.jrebirth.af.component.command.snapshot;
    exports org.jrebirth.af.component.ui.beans;
    exports org.jrebirth.af.component.resources;
    exports org.jrebirth.af.component.behavior.dockable.impl;

    opens org.jrebirth.af.component.ui.tab to org.jrebirth.af.core;
    opens org.jrebirth.af.component.ui.dock to org.jrebirth.af.core;
    opens org.jrebirth.af.component.ui.stack to org.jrebirth.af.core;
    opens org.jrebirth.af.component.ui.workbench to org.jrebirth.af.core;
    
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.preloader;
    requires org.jrebirth.af.core;
    
    
}
