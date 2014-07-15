package org.jrebirth.af.processor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic.Kind;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.jrebirth.af.modular.model.Module;
import org.jrebirth.af.modular.model.ObjectFactory;
import org.jrebirth.af.modular.model.Registration;
import org.jrebirth.af.processor.annotation.Register;
import org.jrebirth.af.processor.annotation.RegistrationPoint;

/**
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ComponentProcessor extends AbstractProcessor {

    private static String MODULE_CONFIG_FILE_NAME= "JRAF-INF/module.xml";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        
        return new HashSet<>(Arrays.asList(Register.class.getName(), RegistrationPoint.class.getName()));
        
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        if (roundEnv.processingOver())
//            return false;

        Map<String, Set<String>> services = new HashMap<String, Set<String>>();

        Elements elements = processingEnv.getElementUtils();

        // discover services from the current compilation sources
        for (Element e : roundEnv.getElementsAnnotatedWith(Register.class)) {
            
            Register a = e.getAnnotation(Register.class);
            if (a == null)
                continue; // input is malformed, ignore
            
            if (!e.getKind().isClass() && !e.getKind().isInterface())
                continue; // ditto
            
            TypeElement type = (TypeElement) e;
            TypeElement contract = getContract(type, a);
            if (contract == null)
                continue; // error should have already been reported

            String cn = elements.getBinaryName(contract).toString();
            Set<String> v = services.get(cn);
            if (v == null)
                services.put(cn, v = new TreeSet<String>());
            
            
            v.add(elements.getBinaryName(type).toString());
            
            
//            ClassPool pool = ClassPool.getDefault();
//            CtClass cc;
//            try {
//                //cc = pool.get(elements.getBinaryName(type).toString());
//                cc = pool.getCtClass(elements.getBinaryName(type).toString());
//                CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
//                f.setModifiers(java.lang.reflect.Modifier.PUBLIC);
//                cc.addField(f);
//                
//                cc.writeFile();
//                
//            } catch (NotFoundException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            } catch (CannotCompileException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            } catch (IOException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
            
        }
        
        Module module = null;
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance("org.jrebirth.af.modular.model");
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final Marshaller marshaller = jaxbContext.createMarshaller();

            final InputStreamReader in = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(MODULE_CONFIG_FILE_NAME), Charset.forName("UTF-8"));
            final XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(in);

            module = Module.class.cast(JAXBElement.class.cast(unmarshaller.unmarshal(xsr)).getValue());


        ObjectFactory factory = new ObjectFactory();
        
        // also load up any existing values, since this compilation may be partial
        Filer filer = processingEnv.getFiler();
        for (Map.Entry<String, Set<String>> e : services.entrySet()) {

            Registration r = factory.createRegistration();
            r.setClazz(e.getValue().toString());
            
            if(module.getRegistrations() == null){
                module.setRegistrations(factory.createRegistrationList());
            }
            module.getRegistrations().getRegistration().add(r);
            
            //            try {
//                FileObject f = filer.getResource(StandardLocation.CLASS_OUTPUT, "", "JRAF-INF/registration.jraf");
//                BufferedReader r = new BufferedReader(new InputStreamReader(f.openInputStream(), "UTF-8"));
//                String line;
//                while ((line = r.readLine()) != null)
//                    e.getValue().add(line);
//                
//                r.close();
//            } catch (FileNotFoundException x) {
//                // doesn't exist
//            } catch (IOException x) {
//                processingEnv.getMessager().printMessage(Kind.ERROR, "Failed to load existing service definition files: " + x);
//            }
        }
            final OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(MODULE_CONFIG_FILE_NAME), Charset.forName("UTF-8"));
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( out );
            marshaller.marshal( module, xmlStreamWriter );
                
    
        } catch (final JAXBException | XMLStreamException | FactoryConfigurationError | FileNotFoundException e) {
            //LOGGER.error("Impossible to open {}.xml", configName, e);
        }
        
//        // now write them back out
//        for (Map.Entry<String, Set<String>> e : services.entrySet()) {
//            try {
//                processingEnv.getMessager().printMessage(Kind.NOTE, "Writing JRAF-INF/registration.jraf");
//                FileObject f = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "JRAF-INF/registration.jraf");
//                PrintWriter pw = new PrintWriter(new OutputStreamWriter(f.openOutputStream(), "UTF-8"));
//                for (String value : e.getValue())
//                    pw.println(value);
//                pw.close();
//            } catch (IOException x) {
//                processingEnv.getMessager().printMessage(Kind.ERROR, "Failed to write service definition files: " + x);
//            }
//        }

        return false;
    }

    private TypeElement getContract(TypeElement type, Register a) {
        // explicitly specified?
        try {
            a.value();
            throw new AssertionError();
        } catch (MirroredTypeException e) {
            TypeMirror m = e.getTypeMirror();
            if (m.getKind() == TypeKind.VOID) {
                // contract inferred from the signature
                boolean hasBaseClass = type.getSuperclass().getKind() != TypeKind.NONE && !isObject(type.getSuperclass());
                boolean hasInterfaces = !type.getInterfaces().isEmpty();
                if (hasBaseClass ^ hasInterfaces) {
                    if (hasBaseClass)
                        return (TypeElement) ((DeclaredType) type.getSuperclass()).asElement();
                    return (TypeElement) ((DeclaredType) type.getInterfaces().get(0)).asElement();
                }

                error(type, "Contract type was not specified, but it couldn't be inferred.");
                return null;
            }

            if (m instanceof DeclaredType) {
                DeclaredType dt = (DeclaredType) m;
                return (TypeElement) dt.asElement();
            } else {
                error(type, "Invalid type specified as the contract");
                return null;
            }
        }

    }

    private boolean isObject(TypeMirror t) {
        if (t instanceof DeclaredType) {
            DeclaredType dt = (DeclaredType) t;
            return ((TypeElement) dt.asElement()).getQualifiedName().toString().equals("java.lang.Object");
        }
        return false;
    }

    private void error(Element source, String msg) {
        processingEnv.getMessager().printMessage(Kind.ERROR, msg, source);
    }
}
