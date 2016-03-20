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

import java.io.IOException;
import java.net.URL;
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
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.processor.annotation.bean.Bean;
import org.jrebirth.af.processor.util.FXBeanDefinition;
import org.jrebirth.af.processor.util.FXPropertyDefinition;

/**
 * The Class BeanProcessor.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BeanProcessor extends AbstractProcessor {

    /** The module config path. */
    private static String MODULE_CONFIG_PATH = "JRAF-INF";

    /** The module config file name. */
    private static String MODULE_CONFIG_FILE_NAME = "module.xml";

    // /** The factory. */
    // private ObjectFactory factory;
    //
    // /** The jaxb context. */
    // private JAXBContext jaxbContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        // this.factory = new ObjectFactory();
        // try {
        // this.jaxbContext = JAXBContext.newInstance("org.jrebirth.af.modular.model", Thread.currentThread().getContextClassLoader());
        // } catch (final JAXBException e) {
        // e.printStackTrace();
        // }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        return new HashSet<>(Arrays.asList(Register.class.getName(), RegistrationPoint.class.getName()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        final Map<String, VariableElement> fields = new ConcurrentHashMap<String, VariableElement>();

        final Map<String, ExecutableElement> methods = new ConcurrentHashMap<String, ExecutableElement>();

        for (final Element e : roundEnv.getElementsAnnotatedWith(Bean.class)) {

            if (e.getKind() == ElementKind.CLASS) {

                final FXBeanDefinition beanDef = new FXBeanDefinition();

                final TypeElement classElement = (TypeElement) e;
                final PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annotated class: " + classElement.getQualifiedName(), e);

                // fqClassName = classElement.getQualifiedName().toString();
                beanDef.setClassName(classElement.getSimpleName().toString());
                beanDef.setPackageName(packageElement.getQualifiedName().toString());

                for (final Element child : classElement.getEnclosedElements()) {

                    if (child.getKind() == ElementKind.METHOD) {

                        final ExecutableElement method = (ExecutableElement) child;

                        final FXPropertyDefinition propertyDef = new FXPropertyDefinition();
                        propertyDef.setType(method.getReturnType().toString());
                        propertyDef.setName(method.getSimpleName().toString());
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

            } else if (e.getKind() == ElementKind.METHOD) {

                final ExecutableElement exeElement = (ExecutableElement) e;

                this.processingEnv.getMessager().printMessage(
                                                              Diagnostic.Kind.NOTE,
                                                              "annotated method: " + exeElement.getSimpleName(), e);

                methods.put(exeElement.getSimpleName().toString(), exeElement);
            }

            try {
                loadTemplate("fqClassName");
            } catch (final IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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

    /**
     * Load template.
     *
     * @param fqClassName the fq class name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void loadTemplate(final String fqClassName) throws IOException {

        if (fqClassName != null) {

            final Properties props = new Properties();
            final URL url = this.getClass().getClassLoader().getResource("velocity.properties");
            try {
                props.load(url.openStream());
            } catch (final IOException e) {
                e.printStackTrace();
            }

            // final VelocityEngine ve = new VelocityEngine(props);
            // ve.init();
            //
            // final VelocityContext vc = new VelocityContext();
            //
            // vc.put("className", "assName");
            // vc.put("packageName", "ckageName");
            // vc.put("fieldselds", "");
            // vc.put("methodsthods", "");
            //
            // final Template vt = ve.getTemplate("beaninfo.vm");
            //
            // final JavaFileObject jfo = this.processingEnv.getFiler().createSourceFile(fqClassName + "BeanInfo");
            //
            // this.processingEnv.getMessager().printMessage(
            // Diagnostic.Kind.NOTE,
            // "creating source file: " + jfo.toUri());
            //
            // final Writer writer = jfo.openWriter();
            //
            // this.processingEnv.getMessager().printMessage(
            // Diagnostic.Kind.NOTE,
            // "applying velocity template: " + vt.getName());
            //
            // vt.merge(vc, writer);

            // writer.close();
        }
    }

}
