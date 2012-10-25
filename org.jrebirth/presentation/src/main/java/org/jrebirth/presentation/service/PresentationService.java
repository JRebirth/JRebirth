package org.jrebirth.presentation.service;

import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.presentation.model.Presentation;

/**
 * The class <strong>PresentationService</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 */
public final class PresentationService extends ServiceBase {

    /** The XML file location. */
    public static final String CONFIG_NAME = "presentation/Presentation";

    /** The root configuration object. */
    private Presentation presentation;

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance("org.jrebirth.presentation.model");
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            final InputStreamReader in = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_NAME + ".xml"), Charset.forName("UTF-8"));
            final XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(in);

            this.presentation = Presentation.class.cast(JAXBElement.class.cast(unmarshaller.unmarshal(xsr)).getValue());

        } catch (final JAXBException | XMLStreamException | FactoryConfigurationError e) {
            System.err.println("Impossible to open " + CONFIG_NAME + ".xml");
            e.printStackTrace();
            throw new CoreException("Xml file incorrectly parsed !", e);
        }
    }

    /**
     * @return Returns the presentation.
     */
    public Presentation getPresentation() {
        return this.presentation;
    }
}
