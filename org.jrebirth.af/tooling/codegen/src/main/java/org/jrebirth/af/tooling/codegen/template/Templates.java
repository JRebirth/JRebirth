/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.tooling.codegen.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.x5.template.Chunk;
import com.x5.template.Theme;

public class Templates {

    private static Map<TemplateName, Chunk> templateMap = new HashMap<>();

    private static Theme theme = new Theme();

    static {
        theme.setClasspathThemesFolder("/templates/");
        theme.setTemplateFolder("/templates");
        theme.setDefaultFileExtension("tpl");
    }

    private static Optional<Chunk> getTemplate(final TemplateName name) {

        if (!templateMap.containsKey(name)) {

            templateMap.put(name, theme.makeChunk("Bean#" + name.name()));
        }
        return Optional.ofNullable(templateMap.get(name));
    }

    public static String use(final TemplateName name) {

        return use(name, null);
    }

    public static String use(final TemplateName name, final Object bean) {

        final Chunk chunk = getTemplate(name).orElseThrow(() -> new RuntimeException());
        chunk.set("bean", bean);
        return chunk.toString();
    }

}
