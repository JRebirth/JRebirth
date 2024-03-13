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

import org.jrebirth.af.api.resource.ResourceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
		String path = m.getName().replace(".", "/") + "/" + resourcePath + resourceName;
		InputStream is = null;
		try {
			is = m.getResourceAsStream(path);
			if (is == null) {
				LOGGER.error("Resource : {} not found into module folder: {}", resourceName, resourcePath);
			}
		} catch (IOException e) {
			LOGGER.error("Impossible to encode path " + path, e);
		}
		return is;
	}

	public static URL getResourceAsURL(Object object, String resourcePath, String resourceName) {
		Module m = getModule(object);
		String path = m.getName().replace(".", "/") + "/" + resourcePath + resourceName;
		URL url = m.getClassLoader().getResource(path);
		if (url == null) {
			LOGGER.error("Resource : {} not found into module folder: {}", resourceName, resourcePath);
		}
		return url; 
	}

	public static Module find(String moduleName) {
		return ModuleLayer.boot().findModule(moduleName).orElse(null);
	}

}
