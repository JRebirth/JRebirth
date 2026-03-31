/**
 * The class <strong>module-info</strong>.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.dialog {
    exports org.jrebirth.af.dialog;
    exports org.jrebirth.af.dialog.simpledialog;
    exports org.jrebirth.af.dialog.basic;

    requires org.jrebirth.af.core;

    requires SimpleDialogFX;

}
