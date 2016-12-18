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
