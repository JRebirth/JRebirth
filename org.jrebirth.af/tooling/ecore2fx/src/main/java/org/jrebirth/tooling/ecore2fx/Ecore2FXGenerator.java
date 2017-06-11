package org.jrebirth.tooling.ecore2fx;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jrebirth.af.tooling.codegen.bean.Class;
import org.jrebirth.af.tooling.codegen.bean.Enum;
import org.jrebirth.af.tooling.codegen.bean.Operation;
import org.jrebirth.af.tooling.codegen.bean.Packageable;
import org.jrebirth.af.tooling.codegen.bean.Property;
import org.jrebirth.af.tooling.codegen.bean.TypeVariable;
import org.jrebirth.af.tooling.codegen.generator.Generators;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
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
import org.jboss.forge.roaster.model.source.JavaEnumSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Ecore2FXGenerator {

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
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Packageable<?>> manageObject(final File output, final EObject obj) throws IOException {

        final List<Packageable<?>> beans = new ArrayList<>();
        if (obj instanceof EPackage) {
            managePackage(output, (EPackage) obj);
        } else if (obj instanceof EClass) {
            beans.add(manageClass((EClass) obj));
        } else if (obj instanceof EEnum) {
            beans.add(manageEnum((EEnum) obj));
        }

        for (final Packageable<?> bean : beans) {

            String formattedSource = "";

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

            if (bean instanceof org.jrebirth.af.tooling.codegen.bean.Enum) {
                formattedSource = Generators.enumGenerator.generate((org.jrebirth.af.tooling.codegen.bean.Enum) bean,
                                                                    javaType instanceof JavaInterfaceSource ? (JavaEnumSource) javaType : Roaster.create(JavaEnumSource.class));
            } else if (bean instanceof Class) {
                if (((Class) bean).isInterface()) {

                    formattedSource = Generators.interfaceGenerator.generate((org.jrebirth.af.tooling.codegen.bean.Class) bean,
                                                                             javaType instanceof JavaInterfaceSource ? (JavaInterfaceSource) javaType : Roaster.create(JavaInterfaceSource.class));
                } else {
                    formattedSource = Generators.beanGenerator.generate((Class) bean, javaType instanceof JavaClassSource ? (JavaClassSource) javaType : Roaster.create(JavaClassSource.class));
                }
            }

            Files.write(formattedSource, temp, Charsets.UTF_8);

        }
        return beans;
    }

    private Enum manageEnum(EEnum obj) {
        final Enum e = Enum.of(getFullName(obj));
        obj.getELiterals().stream().forEach(l -> e.addValue(l.getLiteral()));
        return e;
    }

    private Class manageClass(final EClass cls) {
        final Class bean = new Class();

        bean.isAbstract(cls.isAbstract());
        bean.isInterface(cls.isInterface());

        cls.getETypeParameters().stream()
           .forEach(tp -> bean.addTypeVariable(
                                               TypeVariable.of(tp.getName())
        // .relation(TypeVariable.Kind._extends)
        // .type(Class.of(tp.getEBounds().get(0).getEClassifier().getName()))
        ));

        bean.name(cls.getName());
        bean._package(org.jrebirth.af.tooling.codegen.bean.Package.create().qualifiedName(cls.getEPackage().getName()));

        cls.getEGenericSuperTypes().stream().forEach(st -> {
            if (!getFullName(st).contains("ecore")) {
                if (isExtends(cls, st)) {
                    bean.setSuperType(Class.of(getFullName(st)));

                    if (!st.getETypeArguments().isEmpty()) {

                        st.getETypeArguments().stream().forEach(genericType -> bean.getSuperType().addTypeVariable(
                                                                                                                   TypeVariable.of(getGenericTypeName(genericType))));

                    }

                } else if (isImplements(cls, st)) {
                    final Class implType = Class.of(getFullName(st));

                    st.getETypeArguments().stream().forEach(genericType -> implType.addTypeVariable(
                                                                                                    TypeVariable.of(getGenericTypeName(genericType))));

                    bean.addImplementedType(implType);
                }
            }
        });

        for (final EAttribute attr : cls.getEAttributes()) {

            final Property field = Property.of(cleanName(attr.getName()))
                                           .requireField(true)
                                           .needGetter(true)
                                           .needSetter(true)
                                           .needProperty(true);
            if (attr.getEType() != null) {
                field.type(Class.of(convertEcoreType(attr.getEType())));
            }

            bean.addProperty(field);

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
                bean.addProperty(field);
            }

        }

        for (final EOperation o : cls.getEOperations()) {

            final Operation operation = Operation.of(cleanName(o.getName()))
                                                 .returnType(getReturnType(o))
                                                 .parameters(o.getEParameters().stream()
                                                              .map(p -> org.jrebirth.af.tooling.codegen.bean.Parameter.of(cleanName(p.getName()))
                                                                                                                      .type(p.getEType() != null ? Class.of(convertEcoreType(p.getEType())) : null))
                                                              .collect(Collectors.toList()));

            bean.addOperation(operation);
        }

        return bean;
    }

    private Class getReturnType(EOperation o) {

        if (o.getEGenericType() != null) {
            return Class.of(getGenericTypeName(o.getEGenericType()));
        } else {
            return o.getEType() != null ? Class.of(convertEcoreType(o.getEType())) : null;
        }
    }

    private String getGenericTypeName(EGenericType genericType) {
        if (genericType.getEClassifier() != null) {
            return convertEcoreType(genericType.getEClassifier());
        } else if (genericType.getETypeParameter() != null) {
            return genericType.getETypeParameter().getName();
        }
        return null;
    }

    /**
     * TODO To complete.
     * 
     * @param cls
     * @param st
     */
    private boolean isImplements(final EClass cls, EGenericType st) {
        final EClass cls2 = (EClass) st.getEClassifier();

        return !cls.isInterface() && cls2.isInterface();
    }

    /**
     * TODO To complete.
     * 
     * @param cls
     * @param st
     */
    private boolean isExtends(final EClass cls, EGenericType st) {
        final EClass cls2 = (EClass) st.getEClassifier();
        return cls2.isInterface() && cls.isInterface() || !cls2.isInterface() && !cls.isInterface();
    }

    private String getFullName(final EGenericType eClassifier) {
        return getFullName(eClassifier.getEClassifier());
    }

    private String getFullName(final EClassifier eClassifier) {
        String name = eClassifier.getName();
        EPackage pkg = eClassifier.getEPackage();
        while (pkg != null) {
            name = pkg.getName() + "." + name;
            pkg = pkg.getESuperPackage();
        }
        if (name == null && eClassifier instanceof EDataType) {
            name = getDataType(eClassifier.getClassifierID());
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
    protected String convertEcoreType(final EClassifier type) {
        String result;

        final int typeID = type.getClassifierID();
        result = getDataType(typeID);

        if (result == null) {
            result = getFullName(type);
        }

        return result;
    }

    /**
     * @param typeID
     * @return
     */
    protected String getDataType(final int typeID) {
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
        } else if (typeID == EcorePackage.ESTRING) {
            return "java.lang.String";
        }
        return null;// "java.lang.String";
    }

    private void managePackage(final File output, final EPackage obj) throws IOException {
        for (final EClassifier cls : obj.getEClassifiers()) {
            manageObject(output, cls);
        }

    }
}
