/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2024
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
package org.jrebirth.af.tooling.ecore2fx.test;

import java.io.File;

import org.jrebirth.tooling.ecore2fx.Ecore2FXGenerator;
//import org.junit.jupiter.api.Test;

public class GeneratorTest {

    // @Test
    public void testGenerator() {
        final Ecore2FXGenerator g = new Ecore2FXGenerator();

        final File sourceFolder = new File("target/generated-sources/message");
        if (sourceFolder.exists()) {
            for (final File c : sourceFolder.listFiles()) {
                c.delete();
            }
            sourceFolder.delete();
        }

        g.generate(new File("target/generated-sources"), new File("src/test/resources/Message.ecore"));
    }

}
