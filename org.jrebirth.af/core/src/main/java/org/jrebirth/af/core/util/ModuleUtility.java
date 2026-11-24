/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.util;

import java.io.InputStream;
import java.net.URL;
import java.lang.Module;
import java.lang.ModuleLayer;
import java.util.Optional;

import org.jrebirth.af.api.resource.ResourceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ModuleUtility</strong>.
 *
 * Helpers to resolve classpath resources inside named JPMS modules. Resource items created via
 * {@code Resources.create(...)} live in {@code org.jrebirth.af.core}; when their {@code module()} is
 * unset the primary lookup path is wrong. A layer-wide fallback then searches other application
 * modules (e.g. presentation apps that relocated resources under {@code resources.base.path}).
 *
 * @author Sébastien Bordes
 */
public final class ModuleUtility implements UtilMessages {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleUtility.class);

    /**
     * Private Constructor.
     */
    private ModuleUtility() {
        // Nothing to do
    }

    public static Module getModule(Object object) {
        Module m = null;
        if (object instanceof ResourceItem) {
            m = ((ResourceItem<?, ?, ?>) object).module();
        }
        if (m == null && object != null) {
            m = object.getClass().getModule();
        }
        return m;
    }

    public static InputStream getResourceAsStream(Object object, String resourcePath, String resourceName) {
        Module m = getModule(object);
        String path = getResourcePath(resourcePath, resourceName, m);
        final ClassLoader cl = classLoaderForModule(m, object);
        // Prefer Module.getResourceAsStream so encapsulated module resources (e.g. af.resources
        // images package, not opened) are still readable.
        InputStream is = moduleResourceAsStream(m, path);
        if (is == null) {
            is = cl.getResourceAsStream(path);
        }
        if (is == null) {
            is = searchLayerAsStream(m, resourcePath, resourceName, cl);
        }
        if (is == null) {
            LOGGER.error("Resource : {} not found into '{}' module folder: {}", resourceName,
                    m != null ? m.getName() : "unnamed", path);
        }
        return is;
    }

    private static String getResourcePath(String resourcePath, String resourceName, Module m) {
        if (m != null && m.isNamed()) {
            return m.getName().replace(".", "/") + "/" + resourcePath + resourceName;
        }
        return resourcePath + resourceName;
    }

    public static URL getResourceAsURL(Object object, String resourcePath, String resourceName) {
        Module m = getModule(object);
        String path = getResourcePath(resourcePath, resourceName, m);
        final ClassLoader cl = classLoaderForModule(m, object);
        URL url = moduleResourceAsURL(m, path);
        if (url == null) {
            url = cl.getResource(path);
        }
        if (url == null) {
            url = searchLayerAsURL(m, resourcePath, resourceName, cl);
        }
        if (url == null) {
            LOGGER.error("Resource : {} not found into '{}' module folder: {}", resourceName,
                    m != null ? m.getName() : "unnamed", path);
        }
        return url;
    }

    private static InputStream moduleResourceAsStream(Module m, String path) {
        if (m == null || !m.isNamed() || path == null) {
            return null;
        }
        try {
            return m.getResourceAsStream(path);
        } catch (Exception e) {
            LOGGER.trace("Module.getResourceAsStream failed for {} in {}: {}", path, m.getName(), e.toString());
            return null;
        }
    }

    private static URL moduleResourceAsURL(Module m, String path) {
        if (m == null || !m.isNamed() || path == null) {
            return null;
        }
        // java.lang.Module has getResourceAsStream but no getResource; use its ClassLoader
        // (requires the resource package to be open — see org.jrebirth.af.resources module-info).
        try {
            final ClassLoader cl = m.getClassLoader();
            return cl != null ? cl.getResource(path) : null;
        } catch (Exception e) {
            LOGGER.trace("Module ClassLoader.getResource failed for {} in {}: {}", path, m.getName(), e.toString());
            return null;
        }
    }

    private static InputStream searchLayerAsStream(Module primary, String resourcePath, String resourceName,
            ClassLoader cl) {
        for (Module candidate : candidateModules(primary)) {
            final String path = getResourcePath(resourcePath, resourceName, candidate);
            InputStream is = moduleResourceAsStream(candidate, path);
            if (is == null) {
                is = cl.getResourceAsStream(path);
            }
            if (is != null) {
                LOGGER.debug("Resource : {} resolved via fallback module '{}' path: {}", resourceName,
                        candidate.getName(), path);
                return is;
            }
        }
        // Last resort: classpath root (unnamed / exploded layouts)
        final String rootPath = resourcePath + resourceName;
        return cl.getResourceAsStream(rootPath);
    }

    private static URL searchLayerAsURL(Module primary, String resourcePath, String resourceName, ClassLoader cl) {
        for (Module candidate : candidateModules(primary)) {
            final String path = getResourcePath(resourcePath, resourceName, candidate);
            URL url = moduleResourceAsURL(candidate, path);
            if (url == null) {
                url = cl.getResource(path);
            }
            if (url != null) {
                LOGGER.debug("Resource : {} resolved via fallback module '{}' path: {}", resourceName,
                        candidate.getName(), path);
                return url;
            }
        }
        return cl.getResource(resourcePath + resourceName);
    }

    private static Iterable<Module> candidateModules(Module primary) {
        final ModuleLayer layer = resolveLayer(primary);
        return layer.modules().stream()
                .filter(Module::isNamed)
                .filter(c -> primary == null || !c.equals(primary))
                .filter(ModuleUtility::isApplicationModule)
                .toList();
    }

    private static ModuleLayer resolveLayer(Module primary) {
        if (primary != null && primary.getLayer() != null) {
            return primary.getLayer();
        }
        final Module self = ModuleUtility.class.getModule();
        if (self.getLayer() != null) {
            return self.getLayer();
        }
        return ModuleLayer.boot();
    }

    private static boolean isApplicationModule(Module module) {
        final String name = module.getName();
        return !(name.startsWith("java.")
                || name.startsWith("jdk.")
                || name.startsWith("javafx.")
                || name.startsWith("org.openjfx.")
                || name.startsWith("org.slf4j")
                || name.startsWith("ch.qos.logback")
                || name.startsWith("com.sun.")
                || name.startsWith("jakarta.")
                || name.startsWith("org.glassfish")
                || name.startsWith("io.github."));
    }

    private static ClassLoader classLoaderForModule(Module m, Object contextObject) {
        ClassLoader cl = m != null ? m.getClassLoader() : null;
        if (cl != null) {
            return cl;
        }
        if (contextObject != null) {
            cl = contextObject.getClass().getClassLoader();
            if (cl != null) {
                return cl;
            }
        }
        cl = Thread.currentThread().getContextClassLoader();
        if (cl != null) {
            return cl;
        }
        return ClassLoader.getPlatformClassLoader();
    }

    public static Module find(String moduleName) {
        if (moduleName == null || moduleName.isEmpty()) {
            return null;
        }
        final Module self = ModuleUtility.class.getModule();
        final ModuleLayer layer = self.getLayer();
        if (layer != null) {
            Optional<Module> inAppLayer = layer.findModule(moduleName);
            if (inAppLayer.isPresent()) {
                return inAppLayer.get();
            }
        }
        return ModuleLayer.boot().findModule(moduleName).orElse(null);
    }

}
