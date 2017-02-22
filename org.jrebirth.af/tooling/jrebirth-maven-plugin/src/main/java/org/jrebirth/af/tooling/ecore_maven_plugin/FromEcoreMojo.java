package org.jrebirth.af.tooling.ecore_maven_plugin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.tooling.codegen.bean.Class;
import org.jrebirth.af.tooling.codegen.bean.Property;
import org.jrebirth.af.tooling.codegen.generator.Generators;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * Goal which
 *
 */
@Mojo(name = "fromEcore", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class FromEcoreMojo extends AbstractMojo {

    /**
     * Location of the model file.
     */
    @Parameter(defaultValue = "Model.ecore", property = "jrebirth.ecore", required = true)
    private File ecore;

    /**
     * Specify output directory where the Java files are generated.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException {

        generate(this.outputDirectory, this.ecore);
    }

    /**
     *
     */
    public void generate(final File output, final File ecoreFile) {
        final ResourceSet rs = new ResourceSetImpl();
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());

        if (ecoreFile != null && ecoreFile.exists()) {
            final URI uri = URI.createFileURI(ecoreFile.getAbsolutePath());
            final Resource r = rs.getResource(uri, true);
            final List<EObject> objList = r.getContents();

            for (final EObject obj : objList) {

                try {
                    manageObject(output, obj);
                } catch (final IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Class> manageObject(final File output, final EObject obj) throws IOException {

        final List<Class> beans = new ArrayList<>();
        if (obj instanceof EPackage) {
            managePackage(output, (EPackage) obj);
        } else if (obj instanceof EClass) {
            beans.add(manageClass((EClass) obj));
        }

        for (final Class bean : beans) {

            final String formattedSource;

            final String[] parts = bean._package().qualifiedName().split("\\.");

            File temp = output;
            for (final String n : parts) {

                temp = new File(temp, n);

            }

            temp = new File(temp, bean.name() + ".java");

            Files.createParentDirs(temp);

            JavaType<?> javaType = null;
            if (temp.exists()) {
                javaType = Roaster.parse(temp);
            }

            if (bean.isInterface()) {

                formattedSource = Generators.interfaceGenerator.generate(bean, javaType instanceof JavaInterfaceSource ? (JavaInterfaceSource) javaType : Roaster.create(JavaInterfaceSource.class));
            } else {
                formattedSource = Generators.beanGenerator.generate(bean, javaType instanceof JavaClassSource ? (JavaClassSource) javaType : Roaster.create(JavaClassSource.class));
            }

            Files.write(formattedSource, temp, Charsets.UTF_8);

        }
        return beans;
    }

    private Class manageClass(final EClass cls) {
        final Class bean = new Class();

        bean.isInterface(cls.isInterface());

        bean.name(cls.getName());
        bean._package(org.jrebirth.af.tooling.codegen.bean.Package.create().qualifiedName(cls.getEPackage().getName()));

        if (!cls.getESuperTypes().isEmpty()) {
            if (!getFullName(cls.getESuperTypes().get(0)).contains("ecore")) {

                final Class td = new Class();
                td.name(cls.getESuperTypes().get(0).getName());
                td._package(org.jrebirth.af.tooling.codegen.bean.Package.create().qualifiedName(
                                                                                                getFullName(cls.getESuperTypes().get(0)).substring(0,
                                                                                                                                                   getFullName(cls.getESuperTypes().get(0))
                                                                                                                                                                                           .indexOf(td.name())
                                                                                                                                                           - 1)));

                bean.setSuperType(td);
            }
        }

        for (final EAttribute attr : cls.getEAttributes()) {

            final Property field = Property.of(cleanName(attr.getName()))
                                           .requireField(true)
                                           .needGetter(true)
                                           .needSetter(true)
                                           .needProperty(true)
                                           .type(Class.of(convertEcoreType(attr)));

            bean.properties().add(field);

        }

        for (final EReference ref : cls.getEReferences()) {

            final Property field = Property.of(cleanName(ref.getName()))
                                           .requireField(true)
                                           .needGetter(true)
                                           .needSetter(true)
                                           .needProperty(true)
                                           .type(Class.of(getFullName(ref.getEReferenceType())));

            // Avoid adding ecore stuff
            if (!field.type().qualifiedName().contains("ecore")) {
                bean.properties().add(field);
            }

        }

        return bean;
    }

    private String getFullName(final EClass eClass) {
        String name = eClass.getName();
        EPackage pkg = eClass.getEPackage();
        while (pkg != null) {
            name = pkg.getName() + "." + name;
            pkg = pkg.getESuperPackage();
        }
        return name;

    }

    private String cleanName(final String name) {

        final String[] KEY_WORD = new String[] {
                "abstract", "continue", "for", "new", "switch",
                "assert", "default", "goto", "package", "synchronized",
                "boolean", "do", "if", "private", "this",
                "break", "double", "implements", "protected", "throw",
                "byte", "else", "import", "public", "throws",
                "case", "enum", "instanceof", "return", "transient",
                "catch", "extends", "int", "short", "try",
                "char", "final", "interface", "static", "void",
                "class", "finally", "long", "strictfp", "volatile",
                "const", "float", "native", "super", "while" };

        final List<String> all = Arrays.asList(KEY_WORD);

        if (all.contains(name)) {
            return "_" + name;
        }

        return name;
    }

    /**
     * @param attr
     * @return
     */
    protected String convertEcoreType(final EAttribute attr) {
        final Object result;

        final int typeID = attr.getEType().getClassifierID();
        if (typeID == EcorePackage.EINTEGER_OBJECT || typeID == EcorePackage.EINT) {
            return "int";
        } else if (typeID == EcorePackage.ELONG_OBJECT || typeID == EcorePackage.ELONG) {
            return "long";
        } else if (typeID == EcorePackage.ESHORT_OBJECT || typeID == EcorePackage.ESHORT) {
            return "int";
        } else if (typeID == EcorePackage.EFLOAT_OBJECT || typeID == EcorePackage.EFLOAT) {
            return "float";
        } else if (typeID == EcorePackage.EDOUBLE_OBJECT || typeID == EcorePackage.EDOUBLE) {
            return "double";
        } else if (typeID == EcorePackage.EBYTE_OBJECT || typeID == EcorePackage.EBYTE) {
            return "byte";
        } else if (typeID == EcorePackage.EBIG_INTEGER) {
            return BigInteger.class.getCanonicalName();
        } else if (typeID == EcorePackage.EBIG_DECIMAL) {
            return BigDecimal.class.getCanonicalName();
        }
        return "java.lang.String";
    }

    private void managePackage(final File output, final EPackage obj) throws IOException {
        for (final EClassifier cls : obj.getEClassifiers()) {
            manageObject(output, cls);
        }

    }
}
