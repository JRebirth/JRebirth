import java.io.File;

import org.jrebirth.af.tooling.ecore_maven_plugin.FromEcoreMojo;
import org.junit.Test;

public class GeneratorTest {

	@Test
	public void testGenerator(){
		FromEcoreMojo gg = new FromEcoreMojo();
		
		gg.generate(new File("target/generated-sources"), new File("uml.ecore"));
	}
	
}
