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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.lang.Module;
import java.lang.ModuleLayer;
import java.util.Optional;

import org.jrebirth.af.api.resource.ResourceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ClassUtility</strong>.
 *
 * Some Useful class utilities to perform introspection.
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
        if (m == null) {
            m = object.getClass().getModule();
        }
        return m;
    }

    public static InputStream getResourceAsStream(Object object, String resourcePath, String resourceName) {
        Module m = getModule(object);
        String path = getResourcePath(resourcePath, resourceName, m);
        InputStream is = null;
		try {
			ClassLoader cl = classLoaderForModule(m, object);
			is = cl.getResourceAsStream( URLEncoder.encode(path, "UTF-8"));
			if(is == null) {
				LOGGER.error("Resource : {} not found into module folder: {}", resourceName, resourcePath);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Impossible to encode path " + path, e);
		}
		return is;
    }

	private static String getResourcePath(String resourcePath, String resourceName, Module m) {
		if (m.isNamed()) {
			return m.getName().replace(".", "/") + "/" + resourcePath + resourceName;
		}
		return resourcePath + resourceName;
	}

    public static URL getResourceAsURL(Object object, String resourcePath, String resourceName) {
        Module m = getModule(object);
        String path = getResourcePath(resourcePath, resourceName, m);
        URL url =null;
		try {
			ClassLoader cl = classLoaderForModule(m, object);
			url = cl.getResource(URLEncoder.encode(path, "UTF-8"));
			if(url == null) {
				LOGGER.error("Resource : {} not found into module folder: {}", resourceName, resourcePath);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Impossible to encode path " + path, e);
		}
		return url;
    }

	private static ClassLoader classLoaderForModule(Module m, Object contextObject) {
		ClassLoader cl = m.getClassLoader();
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
