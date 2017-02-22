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
import org.jrebirth.af.tooling.codegen.bean.Class;
import org.jrebirth.af.tooling.codegen.bean.Property;
import org.jrebirth.af.tooling.codegen.generator.Generators;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

/**
 * The Class BeanProcessor.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BeanProcessor extends AbstractProcessor {

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

        return new HashSet<>(Arrays.asList(Bean.class.getName()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        final Map<String, VariableElement> fields = new ConcurrentHashMap<>();

        final Map<String, ExecutableElement> methods = new ConcurrentHashMap<>();

        for (final Element element : roundEnv.getElementsAnnotatedWith(Bean.class)) {

            if (element.getKind() == ElementKind.CLASS) {

                final Bean bean = element.getAnnotation(Bean.class);

                final Class beanDef = new Class();

                final TypeElement classElement = (TypeElement) element;
                final PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annotated class: " + classElement.getQualifiedName(), element);

                // fqClassName = classElement.getQualifiedName().toString();
                beanDef.name(bean.value());
                beanDef._package(org.jrebirth.af.tooling.codegen.bean.Package.create().qualifiedName(packageElement.getQualifiedName().toString()));

                final Class td = new Class();
                td.qualifiedName(classElement.getQualifiedName().toString());

                beanDef.setSuperType(td);

                for (final Element child : classElement.getEnclosedElements()) {

                    if (child.getKind() == ElementKind.METHOD) {

                        final ExecutableElement method = (ExecutableElement) child;

                        final Property propertyDef = new Property();
                        propertyDef.type(Class.of(method.getReturnType().toString()));
                        propertyDef.name(method.getSimpleName().toString());
                        beanDef.properties().add(propertyDef);

                    } else if (child.getKind().isField()) {

                        final VariableElement field = (VariableElement) child;

                        final Property propertyDef = new Property();
                        propertyDef.type(Class.of(getClassName(field)));
                        propertyDef.name(field.getSimpleName().toString());
                        beanDef.properties().add(propertyDef);

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

                try {
                    final String formattedSource = Generators.beanGenerator.generate(beanDef, Roaster.create(JavaClassSource.class));

                    final JavaFileObject jfo = this.processingEnv.getFiler().createSourceFile(beanDef.qualifiedName());
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "creating source file: " + jfo.toUri());

                    final Writer writer = jfo.openWriter();
                    writer.write(formattedSource);
                    writer.close();

                } catch (final Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            } else if (element.getKind() == ElementKind.METHOD) {

                final ExecutableElement exeElement = (ExecutableElement) element;

                this.processingEnv.getMessager().printMessage(
                                                              Diagnostic.Kind.NOTE,
                                                              "annotated method: " + exeElement.getSimpleName(), element);

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

}
