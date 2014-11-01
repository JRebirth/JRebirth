package org.jrebirth.af.modular;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jrebirth.af.modular.model.Module;
import org.jrebirth.af.modular.model.ObjectFactory;
import org.jrebirth.af.modular.model.Registration;
import org.jrebirth.af.modular.model.RegistrationEntry;

public class ModuleConfigFileParser {

    public static List<javafx.util.Pair<Class<?>, Class<?>>> parseFile(final String fileName) {

        final List<javafx.util.Pair<Class<?>, Class<?>>> pairList = new ArrayList<javafx.util.Pair<Class<?>, Class<?>>>();

        final File file = new File(fileName);

        final ObjectFactory factory = new ObjectFactory();
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance("org.jrebirth.af.modular.model", ObjectFactory.class.getClassLoader());

            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            final InputStreamReader in = new InputStreamReader(new FileInputStream(file));
            final XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(in);
            Object o;
            o = unmarshaller.unmarshal(xsr);
            final Module module = Module.class.cast(JAXBElement.class.cast(o).getValue());

            if (module.getRegistrations() != null) {
                for (final Registration rl : module.getRegistrations().getRegistration()) {

                    if (rl.getRegistrationEntries() != null) {
                        for (final RegistrationEntry re : rl.getRegistrationEntries().getRegistrationEntry()) {

                            try {
                                pairList.add(new Pair<Class<?>, Class<?>>(getClassObject(rl.getClazz()), getClassObject(re.getClazz())));
                            } catch (final ClassNotFoundException e) {
                            }
                        }
                    }
                }
            }

        } catch (JAXBException | FileNotFoundException | XMLStreamException | FactoryConfigurationError e) {
            e.printStackTrace();
        }

        return pairList;
    }

    private static Class<?> getClassObject(final String className) throws ClassNotFoundException {

        return Class.forName(className);
    }
}
