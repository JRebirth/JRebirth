/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.tooling.codegen.generator;

import org.jrebirth.af.tooling.codegen.bean.Class;
import org.jrebirth.af.tooling.codegen.bean.Property;
import org.jrebirth.af.tooling.codegen.template.TemplateName;
import org.jrebirth.af.tooling.codegen.template.Templates;

import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class BeanGenerator extends AbstractGenerator<Class, JavaClassSource> {

    /**
     * Package constructor.
     */
    BeanGenerator() {

    }

    @Override
    public String generate(final Class cls, JavaClassSource javaClass) {

        javaClass.setPackage(cls._package().qualifiedName()).setName(cls.name());

        manageGenerics(cls, javaClass);

        // Manage Extends
        if (cls.getSuperType() != null) {
            javaClass.setSuperType(cls.getSuperType().qualifiedNameWithParameter());
        }

        // Manage Implements
        cls.implementedTypes().stream().forEach(i -> javaClass.addInterface(i.qualifiedNameWithParameter()));

        cls.properties().stream().forEach(propDef -> {
            writeField(javaClass, propDef);
            writeProperty(javaClass, propDef);
        });

        writeCreator(javaClass, cls);

        cls.properties().stream().forEach(p -> {
            writeGetter(javaClass, p);
            writeSetter(javaClass, p);
            writePropertyGetter(javaClass, p);

        });

        cls.properties().stream()
           .filter(p -> p.isList())
           .forEach(p -> {
               writeListAdd(javaClass, p);
               writeListRemove(javaClass, p);
           });

        cls.properties().stream()
           .filter(p -> p.isMap())
           .forEach(p -> {
               writeMapPut(javaClass, p);
               writeMapPut(javaClass, p);
           });

        cls.operations().stream().forEach(o -> {
            writeOperation(javaClass, o);
        });

        return formatClass(javaClass);

    }

    private void writeCreator(final JavaClassSource javaClass, final Class beanDef) {

        if (!beanDef.isAbstract() && !javaClass.hasMethodSignature("create")) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("Build a new instance of {@link ").append(beanDef.name()).append("}.");

            final String body = Templates.use(TemplateName.Creator, beanDef);

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName("of")
                                                    .setPublic()
                                                    .setStatic(true)
                                                    .setBody(body)
                                                    .setReturnType(beanDef.qualifiedName());
            method.getJavaDoc().setFullText(javadoc.toString())
                  .addTagValue("@return", "a fresh instance");
        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
            // + body.toString());
        }
    }

    private void writeProperty(final JavaClassSource javaClass, final Property propDef) {
        if (!javaClass.hasField(propDef.getPropertyName())) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("The property for ")
                   .append(propDef.name());

            final FieldSource<?> method = javaClass.addField()
                                                   .setType(propDef.getPropertyType())
                                                   .setName(propDef.getPropertyName())
                                                   .setPrivate();
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
            // + body.toString());
        }
    }

    /**
     * TODO To complete.
     *
     * @param javaClass
     * @param body
     */
    private void writeGetter(final JavaClassSource javaClass, final Property propDef) {
        if (propDef.needGetter()) {
            if (!javaClass.hasMethodSignature(propDef.name())) {

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
                                                        .setName(propDef.name())
                                                        .setPublic()
                                                        .setBody(body)
                                                        .setReturnType(propDef.type().qualifiedName());
                method.getJavaDoc().setFullText(javadoc.toString());

            } else {
                // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
                // + body.toString());
            }
        }
    }

    private void writeSetter(final JavaClassSource javaClass, final Property propDef) {
        if (propDef.needSetter()) {
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
                                                    .setName(propDef.name())
                                                    .setPublic()
                                                    .setBody(body)
                                                    // .setReturnTypeVoid();
                                                    .setReturnType(javaClass.getTypeVariables().isEmpty() ? javaClass.getName() : javaClass.getTypeVariables().get(0).getName());
            method.addParameter(propDef.type().qualifiedName(), propDef.name());
            // method.getJavaDoc().setFullText(javadoc.toString());

            /*
             * } else { javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod( propDef.getName()).getBody() + body.toString()); }
             */
        }
    }

    /**
     * TODO To complete.
     *
     * @param javaClass
     * @param body
     */
    private void writePropertyGetter(final JavaClassSource javaClass, final Property propDef) {
        if (propDef.needGetter() && propDef.needProperty()) {
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
                // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
                // + body.toString());
            }
        }
    }

    private void writeListAdd(final JavaClassSource javaClass, final Property propDef) {

        final String name = "add" + propDef.getUpperName();
        if (!javaClass.hasMethodSignature(name)) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the pSourcePath\n");

            String body = "";

            javaClass.addImport("javafx.collections.FXCollections");
            javaClass.addImport("java.util.Arrays");

            body = Templates.use(TemplateName.PropertyAddList, propDef);

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(name)
                                                    .setPublic()
                                                    .setBody(body.toString())
                                                    .setReturnType(javaClass.getTypeVariables().isEmpty() ? javaClass.getName() : javaClass.getTypeVariables().get(0).getName());

            method.addParameter(propDef.subtype(), propDef.name()).setVarArgs(true);

            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
            // + body.toString());
        }
    }

    private void writeListRemove(final JavaClassSource javaClass, final Property propDef) {

        final String name = "remove" + propDef.getUpperName();
        if (!javaClass.hasMethodSignature(name)) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the pSourcePath\n");

            String body = "";

            javaClass.addImport("javafx.collections.FXCollections");
            javaClass.addImport("java.util.Arrays");

            body = Templates.use(TemplateName.PropertyRemoveList, propDef);

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(name)
                                                    .setPublic()
                                                    .setBody(body.toString())
                                                    .setReturnType(javaClass.getTypeVariables().isEmpty() ? javaClass.getName() : javaClass.getTypeVariables().get(0).getName());

            method.addParameter(propDef.subtype(), propDef.name()).setVarArgs(true);

            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
            // + body.toString());
        }
    }

    private void writeMapPut(final JavaClassSource javaClass, final Property propDef) {

        final String name = "put" + propDef.getPropertyName();
        if (!javaClass.hasMethodSignature(name)) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the pSourcePath\n");

            String body = "";

            javaClass.addImport("javafx.collections.FXCollections");
            body = Templates.use(TemplateName.PropertyPutMap, propDef);

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(name)
                                                    .setPublic()
                                                    .setBody(body.toString())
                                                    .setReturnType(javaClass.getTypeVariables().isEmpty() ? javaClass.getName() : javaClass.getTypeVariables().get(0).getName());
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
            // + body.toString());
        }
    }

    private void writeMapRemove(final JavaClassSource javaClass, final Property propDef) {

        final String name = "remove" + propDef.getPropertyName();
        if (!javaClass.hasMethodSignature(name)) {

            final StringBuilder javadoc = new StringBuilder();
            javadoc
                   .append("@return the pSourcePath\n");

            String body = "";

            javaClass.addImport("javafx.collections.FXCollections");
            body = Templates.use(TemplateName.PropertyRemoveMap, propDef);

            final MethodSource<?> method = javaClass.addMethod()
                                                    .setName(name)
                                                    .setPublic()
                                                    .setBody(body.toString())
                                                    .setReturnType(javaClass.getTypeVariables().isEmpty() ? javaClass.getName() : javaClass.getTypeVariables().get(0).getName());
            method.getJavaDoc().setFullText(javadoc.toString());

        } else {
            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody()
            // + body.toString());
        }
    }

}
