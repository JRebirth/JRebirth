package org.jrebirth.af.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.jrebirth.af.modular.model.Module;
import org.jrebirth.af.modular.model.ObjectFactory;
import org.jrebirth.af.modular.model.Registration;
import org.jrebirth.af.modular.model.RegistrationEntry;
import org.jrebirth.af.processor.annotation.Register;
import org.jrebirth.af.processor.annotation.RegistrationPoint;

/**
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ComponentProcessor extends AbstractProcessor {

    private static String MODULE_CONFIG_PATH = "JRAF-INF";

    private static String MODULE_CONFIG_FILE_NAME = "module.xml";

    private ObjectFactory factory;
    private JAXBContext jaxbContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        factory = new ObjectFactory();
        try {
            jaxbContext = JAXBContext.newInstance("org.jrebirth.af.modular.model", ObjectFactory.class.getClassLoader());
        } catch (final JAXBException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        return new HashSet<>(Arrays.asList(Register.class.getName(), RegistrationPoint.class.getName()));

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        final Map<String, Set<String>> services = new HashMap<String, Set<String>>();

        final Elements elements = processingEnv.getElementUtils();

        final Set<? extends Element> registrationPoints = roundEnv.getElementsAnnotatedWith(RegistrationPoint.class);
        final Set<? extends Element> registrations = roundEnv.getElementsAnnotatedWith(Register.class);

        // Load
        Module module = null;// loadModuleFile();
        if (module == null) {
            module = createModule();
        }

        //
        for (final Element element : registrationPoints) {

            final RegistrationPoint registrationPoint = element.getAnnotation(RegistrationPoint.class);
            // Only managed annotated interfaces
            if (registrationPoint != null && element.getKind().isInterface()) {

                final Registration r = factory.createRegistration();
                r.setClazz(getClassName(element));
                r.setExclusive(registrationPoint.exclusive());

                module.getRegistrations().getRegistration().add(r);

                // processingEnv.getMessager().printMessage(Kind.MANDATORY_WARNING, r.toString(), element);
            }

        }

        for (final Element element : registrations) {

            final Register registration = element.getAnnotation(Register.class);

            // Only managed annotated concrete classes
            if (registration != null && !element.getKind().isInterface() && element.getKind().isClass()) {

                final RegistrationEntry re = factory.createRegistrationEntry();
                re.setClazz(getClassName(element));
                re.setPriority(registration.priority().name());

                String registeredClass;
                TypeMirror value = null;
                try {
                    registration.value();
                } catch (MirroredTypeException mte) {
                    value = mte.getTypeMirror();
                }

                registeredClass = getClassName(value);

                for (Registration r : module.getRegistrations().getRegistration()) {
                    
                    
                    if (registeredClass != null && registeredClass.equals(r.getClazz())) {
                        if(r.getRegistrationEntries() == null){
                            r.setRegistrationEntries(factory.createRegistrationEntryList());
                        }
                        r.getRegistrationEntries().getRegistrationEntry().add(re);
                    }
                }

                // processingEnv.getMessager().printMessage(Kind.MANDATORY_WARNING, re.toString(), element);
            }

        }

        // // also load up any existing values, since this compilation may be partial
        // Filer filer = processingEnv.getFiler();
        // for (Map.Entry<String, Set<String>> e : services.entrySet()) {
        //
        // Registration r = factory.createRegistration();
        // r.setClazz(e.getValue().toString());
        //
        // if (module.getRegistrations() == null) {
        // module.setRegistrations(factory.createRegistrationList());
        // }
        // module.getRegistrations().getRegistration().add(r);
        //
        // }

        saveModule(module);

        return true;
    }

    private void saveModule(Module module) {

        OutputStreamWriter out;
        try {

            FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", MODULE_CONFIG_PATH + "/" + MODULE_CONFIG_FILE_NAME);

            // final File path = new File(MODULE_CONFIG_PATH);
            // path.mkdirs();

            // out = new OutputStreamWriter(new FileOutputStream(MODULE_CONFIG_PATH + "/" + MODULE_CONFIG_FILE_NAME), Charset.forName("UTF-8"));
            // final XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(out);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //
            // marshaller.marshal(factory.createModule(module), xmlStreamWriter);

            // OutputStream os = new FileOutputStream(MODULE_CONFIG_PATH + "/" + MODULE_CONFIG_FILE_NAME);
            // XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // XMLStreamWriter writer = outputFactory.createXMLStreamWriter(os);

            marshaller.marshal(factory.createModule(module), fileObject.openOutputStream());
            marshaller.marshal(factory.createModule(module), System.out);

            // os.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    private Module createModule() {
        final Module module = factory.createModule();

        module.setRegistrations(factory.createRegistrationList());

        //
        return module;
    }

    private Module loadModuleFile() {

        Module module = null;
        final File f = new File(MODULE_CONFIG_PATH + "/" + MODULE_CONFIG_FILE_NAME);
        if (f.exists()) {
            try {
                final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                final InputStreamReader in = new InputStreamReader(new FileInputStream(f));// InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(MODULE_CONFIG_PATH +
                                                                                           // "/" + MODULE_CONFIG_FILE_NAME), Charset.forName("UTF-8"));
                final XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(in);
                final Object o = unmarshaller.unmarshal(xsr);
                module = Module.class.cast(JAXBElement.class.cast(o).getValue());
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return module;
    }

    private String getClassName(Element element) {
        return getClassName(element.asType());

    }

    private String getClassName(TypeMirror t) {
        String res = null;

        if (t instanceof DeclaredType) {
            final DeclaredType dt = (DeclaredType) t;
            res = ((TypeElement) dt.asElement()).getQualifiedName().toString();
        }

        return res;
    }

    private void error(Element source, String msg) {
        processingEnv.getMessager().printMessage(Kind.ERROR, msg, source);
    }

    // private TypeElement getContract(TypeElement type, Register a) {
    // // explicitly specified?
    // try {
    // a.value();
    // throw new AssertionError();
    // } catch (MirroredTypeException e) {
    // TypeMirror m = e.getTypeMirror();
    // if (m.getKind() == TypeKind.VOID) {
    // // contract inferred from the signature
    // boolean hasBaseClass = type.getSuperclass().getKind() != TypeKind.NONE && !isObject(type.getSuperclass());
    // boolean hasInterfaces = !type.getInterfaces().isEmpty();
    // if (hasBaseClass ^ hasInterfaces) {
    // if (hasBaseClass)
    // return (TypeElement) ((DeclaredType) type.getSuperclass()).asElement();
    // return (TypeElement) ((DeclaredType) type.getInterfaces().get(0)).asElement();
    // }
    //
    // error(type, "Contract type was not specified, but it couldn't be inferred.");
    // return null;
    // }
    //
    // if (m instanceof DeclaredType) {
    // DeclaredType dt = (DeclaredType) m;
    // return (TypeElement) dt.asElement();
    // } else {
    // error(type, "Invalid type specified as the contract");
    // return null;
    // }
    // }
    //
    // }

    // // now write them back out
    // for (Map.Entry<String, Set<String>> e : services.entrySet()) {
    // try {
    // processingEnv.getMessager().printMessage(Kind.NOTE, "Writing JRAF-INF/registration.jraf");
    // FileObject f = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "JRAF-INF/registration.jraf");
    // PrintWriter pw = new PrintWriter(new OutputStreamWriter(f.openOutputStream(), "UTF-8"));
    // for (String value : e.getValue())
    // pw.println(value);
    // pw.close();
    // } catch (IOException x) {
    // processingEnv.getMessager().printMessage(Kind.ERROR, "Failed to write service definition files: " + x);
    // }
    // }

    // ClassPool pool = ClassPool.getDefault();
    // CtClass cc;
    // try {
    // //cc = pool.get(elements.getBinaryName(type).toString());
    // cc = pool.getCtClass(elements.getBinaryName(type).toString());
    // CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
    // f.setModifiers(java.lang.reflect.Modifier.PUBLIC);
    // cc.addField(f);
    //
    // cc.writeFile();
    //
    // } catch (NotFoundException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // } catch (CannotCompileException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // } catch (IOException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
}
