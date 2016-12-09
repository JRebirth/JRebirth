/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
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
package org.jrebirth.af.processor;

import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import org.jrebirth.af.api.annotation.bean.Bean;
import org.jrebirth.af.api.annotation.bean.Getter;
import org.jrebirth.af.api.annotation.bean.Setter;
import org.jrebirth.af.processor.util.FXBeanDefinition;
import org.jrebirth.af.processor.util.FXPropertyDefinition;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Formatter;

/**
 * The Class BeanProcessor.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BeanProcessor extends AbstractProcessor {

    private static final String FORMATTER_PROPERTIES_FILE = "/org.eclipse.jdt.core.prefs";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        return new HashSet<>(Arrays.asList(Bean.class.getName(), Getter.class.getName(), Setter.class.getName(), Properties.class.getName()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        final Map<String, VariableElement> fields = new ConcurrentHashMap<>();

        final Map<String, ExecutableElement> methods = new ConcurrentHashMap<>();

        for (final Element e : roundEnv.getElementsAnnotatedWith(Bean.class)) {

            if (e.getKind() == ElementKind.CLASS) {

                final Bean bean = e.getAnnotation(Bean.class);

                final FXBeanDefinition beanDef = new FXBeanDefinition();

                final TypeElement classElement = (TypeElement) e;
                final PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annotated class: " + classElement.getQualifiedName(), e);

                // fqClassName = classElement.getQualifiedName().toString();
                beanDef.setClassName(bean.value());
                beanDef.setPackageName(packageElement.getQualifiedName().toString());
                beanDef.setSuperType(classElement.getSimpleName().toString());

                for (final Element child : classElement.getEnclosedElements()) {

                    if (child.getKind() == ElementKind.METHOD) {

                        final ExecutableElement method = (ExecutableElement) child;

                        final FXPropertyDefinition propertyDef = new FXPropertyDefinition();
                        propertyDef.setType(method.getReturnType().toString());
                        propertyDef.setName(method.getSimpleName().toString());
                        beanDef.getProperties().add(propertyDef);

                    } else if (child.getKind().isField()) {

                        final VariableElement field = (VariableElement) child;

                        final FXPropertyDefinition propertyDef = new FXPropertyDefinition();
                        propertyDef.setType(getClassName(field));
                        propertyDef.setName(field.getSimpleName().toString());
                        beanDef.getProperties().add(propertyDef);

                    }
                }

                // } else if (e.getKind() == ElementKind.FIELD) {
                //
                // final VariableElement varElement = (VariableElement) e;
                //
                // processingEnv.getMessager().printMessage(
                // Diagnostic.Kind.NOTE,
                // "annotated field: " + varElement.getSimpleName(), e);
                //
                // fields.put(varElement.getSimpleName().toString(), varElement);

                createBeanClass(processingEnv, beanDef);

            } else if (e.getKind() == ElementKind.METHOD) {

                final ExecutableElement exeElement = (ExecutableElement) e;

                this.processingEnv.getMessager().printMessage(
                                                              Diagnostic.Kind.NOTE,
                                                              "annotated method: " + exeElement.getSimpleName(), e);

                methods.put(exeElement.getSimpleName().toString(), exeElement);
            }

        }

        return true;
    }

    /**
     * Gets the class name.
     *
     * @param element the element
     * @return the class name
     */
    private String getClassName(final Element element) {
        return getClassName(element.asType());

    }

    /**
     * Gets the class name.
     *
     * @param t the t
     * @return the class name
     */
    private String getClassName(final TypeMirror t) {
        String res = null;

        if (t instanceof DeclaredType) {
            final DeclaredType dt = (DeclaredType) t;
            res = ((TypeElement) dt.asElement()).getQualifiedName().toString();
            if (!dt.getTypeArguments().isEmpty()) {
                res += "<";
                boolean first = true;
                for (final TypeMirror tm : dt.getTypeArguments()) {
                    if (first) {
                        first = false;
                    } else {
                        res += ",";
                    }
                    res += getClassName(tm);
                }
                res += ">";
            }

        } else if (t instanceof PrimitiveType) {
            res = ((PrimitiveType) t).toString();
        }

        return res;
    }

    /**
     * Error.
     *
     * @param source the source
     * @param msg the msg
     */
    private void error(final Element source, final String msg) {
        this.processingEnv.getMessager().printMessage(Kind.ERROR, msg, source);
    }

    private void createBeanClass(ProcessingEnvironment processingEnv, FXBeanDefinition beanDef) {
        try {

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
            prefs.load(this.getClass().getResourceAsStream(FORMATTER_PROPERTIES_FILE));
            final String formattedSource = Formatter.format(prefs, javaClass);

            System.out.println(formattedSource);

            final JavaFileObject jfo = this.processingEnv.getFiler().createSourceFile(beanDef.getFullClassName());
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "creating source file: " + jfo.toUri());

            final Writer writer = jfo.openWriter();
            writer.write(formattedSource);
            writer.close();

        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    protected void writeProperty(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
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
    protected void writeGetter(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
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

    protected void writeSetter(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
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
    protected void writePropertyGetter(final JavaClassSource javaClass, FXPropertyDefinition propDef) {
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
