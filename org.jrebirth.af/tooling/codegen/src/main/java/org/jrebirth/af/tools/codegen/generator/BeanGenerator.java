package org.jrebirth.af.tools.codegen.generator;

import java.io.IOException;
import java.util.Properties;

import org.jrebirth.af.tools.codegen.bean.FXBeanDefinition;
import org.jrebirth.af.tools.codegen.bean.FXPropertyDefinition;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Formatter;

public class BeanGenerator {

    private static final String FORMATTER_PROPERTIES_FILE = "/org.eclipse.jdt.core.prefs";

    public static String createBeanClass(FXBeanDefinition beanDef) {

        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);

        javaClass.setPackage(beanDef.getPackageName()).setName(beanDef.getClassName());

        javaClass.setSuperType(beanDef.getPackageName() + "." + beanDef.getSuperType());

        beanDef.getProperties().stream().forEach(propDef -> writeProperty(javaClass, propDef));

        beanDef.getProperties().stream().forEach(propDef -> {
            writeGetter(javaClass, propDef);
            writeSetter(javaClass, propDef);
            writePropertyGetter(javaClass, propDef);

        });

        final Properties prefs = new Properties();
        try {
            prefs.load(BeanGenerator.class.getResourceAsStream(FORMATTER_PROPERTIES_FILE));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final String formattedSource = prefs != null ? Formatter.format(prefs, javaClass) : Formatter.format(javaClass);

        // System.out.println(formattedSource);

        return formattedSource;

    }

    public static void writeProperty(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
        if (!javaClass.hasField(propDef.getPropertyName())) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("The property for ")
                   .append(propDef.getName());

            final FieldSource<?> method = javaClass.addField()
                                                   .setType(propDef.getPropertyType())
                                                   .setName(propDef.getPropertyName())
                                                   .setPrivate();
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
        }
    }

    /**
     * TODO To complete.
     * 
     * @param javaClass
     * @param body
     */
    public static void writeGetter(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
        if (!javaClass.hasMethodSignature(propDef.getName())) {

            // /**
            // * @return the sourcePath
            // */
            // public File getSourcePath() {
            // if (pSourcePath != null) {
            // return pSourcePath.get();
            // }
            // return sourcePath;
            // }
            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the sourcePath\n");

            final StringBuilder body = new StringBuilder();
            if (propDef.isList() || propDef.isMap()) {

                javaClass.addImport("java.util.stream.Collectors");
                javaClass.addImport("java.util.ArrayList");

                // if(pLastResult != null) {
                // return pLastResult.stream().collect(Collectors.toList());
                // }

                body
                    .append("if (").append(propDef.getPropertyName()).append(" != null) {\n")
                    .append("return ").append(propDef.getPropertyName()).append(".stream().collect(Collectors.toList());\n")
                    .append("}\n")
                    .append("if (").append(propDef.getName()).append(" == null) {\n")
                    .append("this.").append(propDef.getName()).append(" = new ArrayList<>();\n")
                    .append("}\n")
                    .append("return this.").append(propDef.getName()).append(";");

            } else {
                body
                    .append("if (").append(propDef.getPropertyName()).append(" != null) {\n")
                    .append("return ").append(propDef.getPropertyName()).append(".get();\n")
                    .append("}\n")
                    .append("return this.").append(propDef.getName()).append(";");
            }
            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(propDef.getName())
                                                    .setPublic()
                                                    .setBody(body.toString())
                                                    .setReturnType(propDef.getType());
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
        }
    }

    public static void writeSetter(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
        //
        // /**
        // * @param sourcePath
        // * the sourcePath to set
        // */
        // public void setSourcePath(File sourcePath) {
        // if (pLastResult != null) {
        // pLastResult.setAll(lastResult);
        // }
        // this.sourcePath = sourcePath;
        // }

        // if (!javaClass.hasMethodSignature(propDef.getName())) {
        final StringBuilder javadoc = new StringBuilder();
        javadoc
               .append("@return the sourcePath\n");

        final StringBuilder body = new StringBuilder();

        if (propDef.isList() || propDef.isMap()) {

            body
                .append("if (").append(propDef.getPropertyName()).append(" != null) {\n")
                .append(propDef.getPropertyName()).append(".setAll(").append(propDef.getName()).append(");\n")
                .append("}\n")
                .append("this.").append(propDef.getName()).append(" = ").append(propDef.getName()).append(";")
                .append("return this;");
        } else {
            body
                .append("if (").append(propDef.getPropertyName()).append(" != null) {\n")
                .append(propDef.getPropertyName()).append(".set(").append(propDef.getName()).append(");\n")
                .append("}\n")
                .append("this.").append(propDef.getName()).append(" = ").append(propDef.getName()).append(";")
                .append("return this;");
        }
        final MethodSource<?> method = javaClass.addMethod()
                                                .setName(propDef.getName())
                                                .setPublic()
                                                .setBody(body.toString())
                                                // .setReturnTypeVoid();
                                                .setReturnType(javaClass);
        method.addParameter(propDef.getType(), propDef.getName());
        method.getJavaDoc().setFullText(javadoc.toString());

        /*
         * } else { javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString()); }
         */
    }

    /**
     * TODO To complete.
     * 
     * @param javaClass
     * @param body
     */
    public static void writePropertyGetter(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
        if (!javaClass.hasMethodSignature(propDef.getPropertyName())) {

            //
            // /**
            // * @return the sourcePathPy
            // */
            // public ObjectProperty<File> pSourcePath() {
            // if (pSourcePath == null) {
            // pSourcePath = new SimpleObjectProperty<>();
            // pSourcePath.set(sourcePath);
            // }
            // return pSourcePath;
            // }
            //

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the pSourcePath\n");

            final StringBuilder body = new StringBuilder();

            if (propDef.isList() || propDef.isMap()) {

                javaClass.addImport("javafx.collections.FXCollections");

                body
                    .append("if (").append(propDef.getPropertyName()).append(" == null) {\n")
                    .append(propDef.getPropertyName()).append(" =  ").append(propDef.getConcretePropertyType()).append("(this.").append(propDef.getName()).append("());\n")
                    .append("}\n")
                    .append("return this.").append(propDef.getPropertyName()).append(";");

            } else {

                body
                    .append("if (").append(propDef.getPropertyName()).append(" == null) {\n")
                    .append(propDef.getPropertyName()).append(" = new ").append(propDef.getConcretePropertyType()).append("();\n")
                    .append(propDef.getPropertyName()).append(".set(").append(propDef.getName()).append(");\n")
                    .append("}\n")
                    .append("return this.").append(propDef.getPropertyName()).append(";");
            }

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(propDef.getPropertyName())
                                                    .setPublic()
                                                    .setBody(body.toString())
                                                    .setReturnType(propDef.getPropertyType());
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
        }
    }

}
