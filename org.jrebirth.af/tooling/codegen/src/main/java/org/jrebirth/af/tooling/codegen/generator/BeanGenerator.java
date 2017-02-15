package org.jrebirth.af.tooling.codegen.generator;

import org.jrebirth.af.tooling.codegen.bean.FXBeanDefinition;
import org.jrebirth.af.tooling.codegen.bean.FXPropertyDefinition;
import org.jrebirth.af.tooling.codegen.template.TemplateName;
import org.jrebirth.af.tooling.codegen.template.Templates;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class BeanGenerator extends AbstractGenerator<FXBeanDefinition> {

    /**
     * Package constructor.
     */
    BeanGenerator() {

    }

    @Override
    public String generate(final FXBeanDefinition beanDef) {

        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);

        javaClass.setPackage(beanDef.getPackageName()).setName(beanDef.getClassName());

        if (beanDef.getSuperType() != null) {
            javaClass.setSuperType(/* beanDef.getPackageName() + "." + */beanDef.getSuperType());
        }

        beanDef.getProperties().stream().forEach(propDef -> {
            writeField(javaClass, propDef);
            writeProperty(javaClass, propDef);
        });

        writeCreator(javaClass, beanDef);

        beanDef.getProperties().stream().forEach(propDef -> {
            writeGetter(javaClass, propDef);
            writeSetter(javaClass, propDef);
            writePropertyGetter(javaClass, propDef);

        });

        return formatClass(javaClass);

    }

    private void writeCreator(final JavaClassSource javaClass, final FXBeanDefinition beanDef) {

        if (!javaClass.hasMethodSignature("create")) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("Build a new instance of {@link ").append(beanDef.getClassName()).append("}.");

            final String body = Templates.use(TemplateName.Creator, beanDef);

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName("create")
                                                    .setPublic()
                                                    .setStatic(true)
                                                    .setBody(body)
                                                    .setReturnType(beanDef.getClassName());
            method.getJavaDoc().setFullText(javadoc.toString())
                  .addTagValue("@return", "a fresh instance");
        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
        }
    }

    private void writeField(final JavaClassSource javaClass, final FXPropertyDefinition propDef) {

        if (propDef.requireField()) {
            if (!javaClass.hasField(propDef.getName())) {

                final StringBuilder javadoc = new StringBuilder();
                javadoc
                       .append("The field ")
                       .append(propDef.getName());

                final FieldSource<?> method = javaClass.addField()
                                                       .setType(propDef.getType())
                                                       .setName(propDef.getName())
                                                       .setPrivate();
                method.getJavaDoc().setFullText(javadoc.toString());

            } else {
                // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
            }
        }
    }

    private void writeProperty(final JavaClassSource javaClass, final FXPropertyDefinition propDef) {
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
    private void writeGetter(final JavaClassSource javaClass, final FXPropertyDefinition propDef) {

        if (!javaClass.hasMethodSignature(propDef.getName())) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the sourcePath\n");

            String body = "";

            if (propDef.isList()) {

                javaClass.addImport("java.util.stream.Collectors");
                javaClass.addImport("java.util.ArrayList");

                body = Templates.use(TemplateName.Getter_List, propDef);

            } else if (propDef.isMap()) {

                javaClass.addImport("java.util.stream.Collectors");
                javaClass.addImport("java.util.HashMap");

                body = Templates.use(TemplateName.Getter_Map, propDef);

            } else {

                body = Templates.use(TemplateName.Getter, propDef);

            }

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(propDef.getName())
                                                    .setPublic()
                                                    .setBody(body)
                                                    .setReturnType(propDef.getType());
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
        }
    }

    private void writeSetter(final JavaClassSource javaClass, final FXPropertyDefinition propDef) {

        // if (!javaClass.hasMethodSignature(propDef.getName())) {
        // final StringBuilder javadoc = new StringBuilder();
        // javadoc
        // .append("@return the sourcePath\n");

        String body = "";

        if (propDef.isList()) {

            body = Templates.use(TemplateName.Setter_List, propDef);

        } else if (propDef.isMap()) {
            body = Templates.use(TemplateName.Setter_Map, propDef);
        } else {
            body = Templates.use(TemplateName.Setter, propDef);
        }
        final MethodSource<?> method = javaClass.addMethod()
                                                .setName(propDef.getName())
                                                .setPublic()
                                                .setBody(body)
                                                // .setReturnTypeVoid();
                                                .setReturnType(javaClass);
        method.addParameter(propDef.getType(), propDef.getName());
        // method.getJavaDoc().setFullText(javadoc.toString());

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
    private void writePropertyGetter(final JavaClassSource javaClass, final FXPropertyDefinition propDef) {
        if (!javaClass.hasMethodSignature(propDef.getPropertyName())) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the pSourcePath\n");

            String body = "";

            if (propDef.isList()) {

                javaClass.addImport("javafx.collections.FXCollections");
                body = Templates.use(TemplateName.PropertyGetter_List, propDef);

            } else if (propDef.isMap()) {

                javaClass.addImport("javafx.collections.FXCollections");
                body = Templates.use(TemplateName.PropertyGetter_Map, propDef);

            } else {

                body = Templates.use(TemplateName.PropertyGetter, propDef);

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
