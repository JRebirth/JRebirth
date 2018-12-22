/**
 * The class <strong>CodeGen</strong>.
 * 
 * @author Sébastien Bordes
 */
module org.jrebirth.af.tooling.codegen {

    exports org.jrebirth.af.tooling.codegen.template;
    exports org.jrebirth.af.tooling.codegen.bean;
    exports org.jrebirth.af.tooling.codegen.generator;
	requires codeshaded;
//
//    requires chunk.templates;
//    requires roaster.api;
//    requires roaster.jdt;
    
}
