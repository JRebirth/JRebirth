package org.jrebirth.af.tooling.codegen.generator;

import java.io.IOException;
import java.util.Properties;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.util.Formatter;

public abstract class AbstractGenerator<B> implements CodeGenerator<B> {

    /**
     * @param javaClass
     *
     * @return
     */
    protected String formatClass(final JavaClassSource javaClass) {
        final Properties prefs = new Properties();
        try {
            prefs.load(BeanGenerator.class.getResourceAsStream(FORMATTER_PROPERTIES_FILE));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final String formattedSource = prefs != null ? Formatter.format(prefs, javaClass) : Formatter.format(javaClass);

        // System.out.println(formattedSource);

        return formattedSource;
    }

}
