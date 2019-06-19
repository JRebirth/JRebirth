import java.io.File;

import org.jrebirth.tooling.ecore2fx.Ecore2FXGenerator;

import org.junit.Test;

public class GeneratorTest {

    @Test
    public void testGenerator() {
        final Ecore2FXGenerator g = new Ecore2FXGenerator();

        final File sourceFolder = new File("target/generated-sources/message");
        if (sourceFolder.exists()) {
            for (final File c : sourceFolder.listFiles()) {
                c.delete();
            }
            sourceFolder.delete();
        }

        g.generate(new File("target/generated-sources"), new File("src/test/resources/Message.ecore"), false);
    }

}
