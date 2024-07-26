module org.jrebirth.af.tooling.ecore2fx {

    exports org.jrebirth.tooling.ecore2fx;

    requires com.google.common;
    requires com.google.errorprone.annotations;
    requires org.eclipse.emf.common;
    requires org.eclipse.emf.ecore;
    requires org.eclipse.emf.ecore.xmi;
    requires org.jrebirth.af.tooling.codegen;
    requires roaster.api;

    requires transitive javafx.base;
}
