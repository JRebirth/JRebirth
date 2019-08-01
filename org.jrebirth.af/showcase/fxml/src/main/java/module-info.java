/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.showcase.fxml {

    exports org.jrebirth.af.showcase.fxml;
    
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.jrebirth.af.core ;
    requires org.jrebirth.af.component;
    
//    exports org.jrebirth.af.showcase.fxml.ui.main ;
//    exports org.jrebirth.af.showcase.fxml.ui.embedded ;
//    exports org.jrebirth.af.showcase.fxml.ui.hybrid ;
//    exports org.jrebirth.af.showcase.fxml.ui.included ;
//    exports org.jrebirth.af.showcase.fxml.ui.standalone ;
    
    opens org.jrebirth.af.showcase.fxml.ui.main;
    opens org.jrebirth.af.showcase.fxml.ui.embedded ;
    opens org.jrebirth.af.showcase.fxml.ui.hybrid ;
    opens org.jrebirth.af.showcase.fxml.ui.included ;
    opens org.jrebirth.af.showcase.fxml.ui.standalone ;

    requires org.slf4j;
}
