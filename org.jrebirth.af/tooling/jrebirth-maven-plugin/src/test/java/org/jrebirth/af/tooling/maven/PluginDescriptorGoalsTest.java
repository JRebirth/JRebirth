package org.jrebirth.af.tooling.maven;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

/**
 * Guards against shipping a plugin JAR whose descriptor lists no goals (Central 12.0.0 regression).
 */
public class PluginDescriptorGoalsTest {

    @Test
    public void pluginDescriptorListsText2fxAndEcore2fxGoals() throws IOException {
        Path descriptor = Path.of("target", "classes", "META-INF", "maven", "plugin.xml");
        String xml;
        if (Files.isRegularFile(descriptor)) {
            xml = Files.readString(descriptor, StandardCharsets.UTF_8);
        } else {
            try (InputStream in = PluginDescriptorGoalsTest.class.getResourceAsStream("/META-INF/maven/plugin.xml")) {
                assertTrue("plugin.xml must exist after package", in != null);
                xml = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
        }
        assertTrue("plugin.xml must declare text2fx goal", xml.contains("<goal>text2fx</goal>"));
        assertTrue("plugin.xml must declare ecore2fx goal", xml.contains("<goal>ecore2fx</goal>"));
        assertTrue("plugin.xml must not ship with empty mojos", !xml.contains("<mojos/>"));
    }
}