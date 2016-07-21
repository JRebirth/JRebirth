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

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jrebirth.af.api.annotation.Preload;
import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.module.BootComponent;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.core.module.AbstractModuleStarter;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Formatter;

/**
 * The Class ComponentProcessor.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ComponentProcessor extends AbstractProcessor {

    private static final String JREBIRTH_PROPERTIES_PATH = "jrebirth.properties";
    private static final String MODULE_STARTER_CLASS_PROPERTY = "moduleStarterClass";
    private static final String FORMATTER_PROPERTIES_FILE = "/org.eclipse.jdt.core.prefs";
    private static final String MODULE_STARTER_SPI_PATH = "META-INF/services/org.jrebirth.af.api.module.ModuleStarter";

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

        return new HashSet<>(Arrays.asList(Preload.class.getName(), BootComponent.class.getName(), Register.class.getName(), RegistrationPoint.class.getName()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        final Map<String, Set<String>> services = new HashMap<String, Set<String>>();

        final Elements elements = this.processingEnv.getElementUtils();

        final Map<Class<?>, Set<? extends Element>> annotationMap = new HashMap<>();
        annotationMap.put(Preload.class, roundEnv.getElementsAnnotatedWith(Preload.class));
        annotationMap.put(BootComponent.class, roundEnv.getElementsAnnotatedWith(BootComponent.class));
        annotationMap.put(RegistrationPoint.class, roundEnv.getElementsAnnotatedWith(RegistrationPoint.class));
        annotationMap.put(Register.class, roundEnv.getElementsAnnotatedWith(Register.class));
        if (!annotationMap.get(BootComponent.class).isEmpty() ||
                !annotationMap.get(RegistrationPoint.class).isEmpty() ||
                !annotationMap.get(Register.class).isEmpty() ||
                !annotationMap.get(Preload.class).isEmpty()) {

            final String moduleName = createModuleStarter(annotationMap, this.processingEnv);
            createSPIFile(moduleName);
        }

        return true;
    }

    private void createSPIFile(String moduleName) {
        FileObject fo;
        try {
            fo = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", MODULE_STARTER_SPI_PATH);
            this.processingEnv.getMessager().printMessage(
                                                          Diagnostic.Kind.NOTE,
                                                          "creating spi file: " + fo.toUri());

            final Writer writer = fo.openWriter();
            writer.write(moduleName);
            writer.close();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Save module.
     *
     * @param module the module
     */
    private String createModuleStarter(final Map<Class<?>, Set<? extends Element>> annotationMap, ProcessingEnvironment processingEnv) {
        String moduleName = null;
        try {
            moduleName = getModuleStarterName(processingEnv);
            String starterName = moduleName.substring(moduleName.lastIndexOf('.'));
            String pkg = moduleName.replace(starterName, "");

            final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);

            javaClass.setPackage(pkg).setName(starterName);

            javaClass.extendSuperType(AbstractModuleStarter.class);

            final StringBuilder body = new StringBuilder();

            appendRegistrations(javaClass, body, annotationMap.get(Register.class));

            appendPreload(javaClass, body, annotationMap.get(Preload.class));

            appendBootComponent(javaClass, body, annotationMap.get(BootComponent.class));

            if (!javaClass.hasMethodSignature("start")) {
                final MethodSource<?> method = javaClass.addMethod()
                                                        .setName("start")
                                                        .setPublic()
                                                        .setBody(body.toString())
                                                        .setReturnTypeVoid();
                method.getJavaDoc().setFullText("{@inheritDoc}");
                method.addAnnotation(Override.class);

            } else {
                javaClass.getMethod("start").setBody(javaClass.getMethod("start").getBody() + body.toString());
            }

            final Properties prefs = new Properties();
            prefs.load(this.getClass().getResourceAsStream(FORMATTER_PROPERTIES_FILE));
            final String formattedSource = Formatter.format(prefs, javaClass);

            System.out.println(formattedSource);

            JavaFileObject jfo;
            try {
                jfo = this.processingEnv.getFiler().createSourceFile(moduleName);
                this.processingEnv.getMessager().printMessage(
                                                              Diagnostic.Kind.NOTE,
                                                              "creating source file: " + jfo.toUri());

                final Writer writer = jfo.openWriter();
                writer.write(formattedSource);
                writer.close();

            } catch (final IOException e) {
                e.printStackTrace();
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

        return moduleName;
    }

    private void appendPreload(JavaClassSource javaClass, StringBuilder body, Set<? extends Element> list) {
        for (final Element element : list) {

            final Preload p = element.getAnnotation(Preload.class);

            body.append("\npreloadClass(")
                .append(getSimpleClassName(element))
                .append(".class);\n");

            javaClass.addImport(getClassName(element));
        }

    }

    private void appendBootComponent(JavaClassSource javaClass, StringBuilder body, Set<? extends Element> list) {
        for (final Element element : list) {

            final BootComponent bc = element.getAnnotation(BootComponent.class);

            body.append("\nbootComponent(")
                .append(getSimpleClassName(element))
                .append(".class);\n");

            javaClass.addImport(getClassName(element));
        }

    }

    private void appendRegistrations(JavaClassSource javaClass, StringBuilder body, Set<? extends Element> list) {
        for (final Element element : list) {

            final Register r = element.getAnnotation(Register.class);

            TypeMirror value = null;
            try {
                r.value();
            } catch (final MirroredTypeException mte) {
                value = mte.getTypeMirror();
            }

            body.append("\nregister(")
                .append(getSimpleClassName(value))
                .append(".class, ")
                .append(getSimpleClassName(element))
                .append(".class, ")
                .append("PriorityLevel.")
                .append(r.priority().name())
                .append(", ")
                .append(r.weight())
                .append(");\n");

            javaClass.addImport(getClassName(value));
            javaClass.addImport(getClassName(element));
            javaClass.addImport(PriorityLevel.class);
        }

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

    private String getSimpleClassName(final Element element) {
        return getSimpleClassName(element.asType());

    }

    private String getSimpleClassName(final TypeMirror t) {
        String res = null;

        if (t instanceof DeclaredType) {
            final DeclaredType dt = (DeclaredType) t;
            res = ((TypeElement) dt.asElement()).getSimpleName().toString();
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
        this.processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR, msg, source);
    }

    private String getModuleStarterName(ProcessingEnvironment processingEnv) throws IOException, XmlPullParserException, NoSuchFieldException, IllegalAccessException {
        String name = null;

        InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream(JREBIRTH_PROPERTIES_PATH);
        if (propertiesStream != null) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            name = properties.getProperty(MODULE_STARTER_CLASS_PROPERTY);
        }

        if (name == null || name.isEmpty()) {
            name = getModuleStarterNameFromMaven(processingEnv);
        }

        return name;
    }

    private String getModuleStarterNameFromMaven(ProcessingEnvironment processingEnv) throws IOException, NoSuchFieldException, IllegalAccessException, XmlPullParserException {
        final Field f = processingEnv.getClass().getDeclaredField("options");
        f.setAccessible(true);
        final Object options = f.get(processingEnv);
        final Field ff = options.getClass().getDeclaredField("values");
        ff.setAccessible(true);
        final Map opt = (Map) ff.get(options);
        final String outputClasses = (String) opt.get("-d");
        final String baseDir = outputClasses.substring(0, outputClasses.indexOf("target"));
        final MavenXpp3Reader pomReader = new MavenXpp3Reader();
        final Model model = pomReader.read(new FileReader(new File(baseDir + "pom.xml")));

        final String pkg = model.getGroupId() + "." + model.getArtifactId();
        final String starterName = StringUtils.capitalize(model.getArtifactId()) + "ModuleStarter";
        return pkg + "." + starterName;
    }

}
