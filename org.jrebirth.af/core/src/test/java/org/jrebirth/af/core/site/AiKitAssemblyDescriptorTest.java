package org.jrebirth.af.core.site;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class AiKitAssemblyDescriptorTest {

    @Test
    void aiKitAssemblyReferencesKitDirectory() throws Exception {
        Path afRoot = resolveAfReactorRoot();
        Path descriptor = afRoot.resolve("src/assembly/ai-kit.xml");
        Path kitRoot = afRoot.resolve("../jrebirth-ai-kit").normalize();
        assertTrue(Files.isRegularFile(descriptor), () -> "missing " + descriptor);
        String xml = Files.readString(descriptor);
        assertTrue(xml.contains("jrebirth-ai-kit"), "descriptor must package jrebirth-ai-kit");
        assertTrue(Files.isDirectory(kitRoot), () -> "kit tree missing at " + kitRoot);
        assertTrue(Files.isRegularFile(kitRoot.resolve("install-interactive.sh")));
        assertTrue(Files.isRegularFile(kitRoot.resolve("AGENTS.md")));
    }

    private static Path resolveAfReactorRoot() {
        Path cwd = Path.of("").toAbsolutePath().normalize();
        Path[] candidates = {
            cwd,
            cwd.resolve(".."),
            cwd.getParent(),
        };
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