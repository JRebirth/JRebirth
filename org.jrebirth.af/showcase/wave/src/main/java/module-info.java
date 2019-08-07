/**
 * The class <strong>Wave Showcase Module</strong>.
 * 
 * @author Sébastien Bordes
 */
open module org.jrebirth.af.showcase.wave {

    
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires org.jrebirth.af.core;

    requires org.slf4j;

    provides org.jrebirth.af.api.module.ModuleStarter with org.jrebirth.af.showcase.wave.WaveModuleStarter;
    
//    exports org.jrebirth.af.showcase.wave;
//    exports org.jrebirth.af.showcase.wave.ui;
//    exports org.jrebirth.af.showcase.wave.ui.main;
}
