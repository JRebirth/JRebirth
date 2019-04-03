/**
 * The class <strong>module-info</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.tooling.ecore2fx {
    exports org.jrebirth.tooling.ecore2fx;

    requires javafx.base;
    requires org.eclipse.emf.common;
    requires org.eclipse.emf.ecore;
    requires org.eclipse.emf.ecore.xmi;
    requires org.jrebirth.af.tooling.codegen;
    requires com.google.common;

}
