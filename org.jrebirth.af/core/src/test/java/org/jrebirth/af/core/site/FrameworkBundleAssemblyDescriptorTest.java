package org.jrebirth.af.core.site;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class FrameworkBundleAssemblyDescriptorTest {

    @Test
    void frameworkBundleAssemblyIsWiredForSiteDownloads() throws Exception {
        Path afRoot = resolveAfReactorRoot();
        Path descriptor = afRoot.resolve("src/assembly/framework-bundle.xml");
        Path pom = afRoot.resolve("pom.xml");
        assertTrue(Files.isRegularFile(descriptor), () -> "missing " + descriptor);
        String xml = Files.readString(descriptor);
        assertTrue(xml.contains("JRebirth-"), "bundle base directory must be versioned");
        assertTrue(xml.contains("exclude>showcase/"), "descriptor must exclude showcase tree");
        assertTrue(xml.contains("libs"), "descriptor must collect library JARs");

        String pomXml = Files.readString(pom);
        assertTrue(pomXml.contains("build-framework-bundle-zip"), "AF POM must bind framework bundle at pre-site");
        assertTrue(pomXml.contains("framework-bundle.xml"), "AF POM must reference framework-bundle.xml");
        assertTrue(pomXml.contains("jrebirth-${project.version}"), "framework zip finalName must include project version");
    }

    private static Path resolveAfReactorRoot() {
        Path cwd = Path.of("").toAbsolutePath().normalize();
        Path[] candidates = { cwd, cwd.resolve(".."), cwd.getParent() };
        for (Path candidate : candidates) {
            if (candidate != null && Files.isRegularFile(candidate.resolve("src/assembly/ai-kit.xml"))) {
                return candidate.normalize();
            }
        }
        String multiModule = System.getProperty("maven.multiModuleProjectDirectory");
        if (multiModule != null) {
            Path root = Path.of(multiModule).toAbsolutePath().normalize();
            if (Files.isRegularFile(root.resolve("src/assembly/ai-kit.xml"))) {
                return root;
            }
        }
        throw new IllegalStateException("Cannot locate org.jrebirth.af root (src/assembly/ai-kit.xml)");
    }
}