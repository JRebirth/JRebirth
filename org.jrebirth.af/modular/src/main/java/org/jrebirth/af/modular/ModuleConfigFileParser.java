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
package org.jrebirth.af.modular;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

import org.jrebirth.af.modular.model.Component;
import org.jrebirth.af.modular.model.Module;
import org.jrebirth.af.modular.model.ObjectFactory;
import org.jrebirth.af.modular.model.Registration;
import org.jrebirth.af.modular.model.RegistrationEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ModuleConfigFileParser is used to parse Module.xml files.
 */
public final class ModuleConfigFileParser {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleConfigFileParser.class);

    /**
     * Private Constructor.
     */
    private ModuleConfigFileParser() {
        super();
    }

    /**
     * TRy to load a custom resource file.
     *
     * @param custConfFileName the custom resource file to load
     *
     * @return the load input stream
     */
    public static InputStream loadInputStream(final String custConfFileName) {
        InputStream is = null;
        final File resourceFile = new File(custConfFileName);
        // Check if the file could be find
        if (resourceFile.exists()) {
            try {
                is = new FileInputStream(resourceFile);
            } catch (final FileNotFoundException e) {
                // Nothing to do
            }
        } else {
            // Otherwise try to load from context classloader
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(custConfFileName);
        }

        return is;
    }

    /**
     * Parses the Module.xml.
     *
     * @param fileName the module file name
     * 
     * @return the module object
     */
    public static Module parseFile(final String fileName) {

        Module module = null;

        // Reference the Module.xml file
        final File file = new File(fileName);

        final ObjectFactory factory = new ObjectFactory();

        JAXBContext jaxbContext;
        try {
            // Load the JAXB context
            jaxbContext = JAXBContext.newInstance("org.jrebirth.af.modular.model", ObjectFactory.class.getClassLoader());

            // Prepare the unmarshaller
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Create the input stream reader
            final InputStreamReader in = new InputStreamReader(loadInputStream(fileName), StandardCharsets.UTF_8);
            // Create the XML Stream reader
            final XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(in);
            // ANd finally unmarshall the file into a Java object
            final Object o = unmarshaller.unmarshal(xsr);

            // Retrieve the Module object
            if (o instanceof JAXBElement && ((JAXBElement<?>) o).getValue() instanceof Module) {
                module = Module.class.cast(JAXBElement.class.cast(o).getValue());
            }

        } catch (JAXBException | /* FileNotFoundException | */XMLStreamException | FactoryConfigurationError e) {
            LOGGER.error("An error occurred while parsing " + fileName, e);
        }

        return module;
    }

    /**
     * Get all Component Registrations by pair Interface + Implementation.
     *
     * @param fileName the Module file name
     * 
     * @return the registrations
     */
    public static List<Pair<Class<?>, Class<?>>> getRegistrations(final String fileName) {

        // Get the loaded module object
        final Module module = parseFile(fileName);

        final List<Pair<Class<?>, Class<?>>> pairList = new ArrayList<Pair<Class<?>, Class<?>>>();

        if (module != null && module.getRegistrations() != null) {

            // Iterate over all Component Registration
            for (final Registration rl : module.getRegistrations().getRegistration()) {

                if (rl.getRegistrationEntries() != null) {
                    for (final RegistrationEntry re : rl.getRegistrationEntries().getRegistrationEntry()) {

                        try {
                            // Add interface/implementation pair
                            pairList.add(createPair(rl, re));
                        } catch (final ClassNotFoundException e) {
                            LOGGER.error("Impossible to find a Component Class", e);
                        }
                    }
                }
            }
        }
        return pairList;
    }

    /**
     * CReate a pair object with interface+implementation.
     * 
     * @param rl the interface definition
     * @param re the implementation definition
     * 
     * @return the pair object
     * 
     * @throws ClassNotFoundException if one of the class cannot be loaded
     */
    private static Pair<Class<?>, Class<?>> createPair(final Registration rl, final RegistrationEntry re) throws ClassNotFoundException {
        return new Pair<Class<?>, Class<?>>(getClassObject(rl.getClazz()), getClassObject(re.getClazz()));
    }

    /**
     * Get the list of Component to start during WarmUp.
     *
     * @param fileName the module file name
     * 
     * @return the warm up
     */
    public static List<Class<?>> getWarmUp(final String fileName) {

        // Get the loaded module object
        final Module module = parseFile(fileName);

        final List<Class<?>> warmUpList = new ArrayList<Class<?>>();

        if (module != null && module.getWarmUp() != null) {

            // Iterate over Component to load during WarmUp
            for (final Component component : module.getWarmUp().getComponent()) {

                if (component.getClazz() != null) {
                    try {
                        // Add the Component Class
                        warmUpList.add(getClassObject(component.getClazz()));
                    } catch (final ClassNotFoundException e) {
                        LOGGER.error("Impossible to find a Component Class", e);
                    }
                }
            }
        }
        return warmUpList;
    }

    /**
     * Gets the class object.
     *
     * @param className the class name
     * @return the class object
     * 
     * @throws ClassNotFoundException the class not found exception
     */
    private static Class<?> getClassObject(final String className) throws ClassNotFoundException {

        return Class.forName(className);
    }
}
