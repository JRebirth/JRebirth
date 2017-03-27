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
package org.jrebirth.af.api.resource.image;

/**
 * The enumeration <strong>ImageExtension</strong> is used to provides all possible image extension supported by JavaFX toolkit.
 *
 *
 * @author Sébastien Bordes
 */
public enum ImageExtension {

    /** The empty file extension. */
    NONE,

    /** The .png image file extension. */
    PNG,

    /** The .jpg or .jpeg image file extension. */
    JPG,

    /** The .gif image file extension. */
    GIF;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this == NONE ? "" : "." + name().toLowerCase();
    }

    /**
     * Get an ImageExtension value for the given string or return NONE.
     *
     * @param imageExtension the string to convert
     *
     * @return a valid IMageExtension or NONE if the string is invalid
     */
    public static ImageExtension of(final String imageExtension) {
        final ImageExtension ext = ImageExtension.NONE;

        if (imageExtension != null && !imageExtension.isEmpty()) {
            try {
                return ImageExtension.valueOf(imageExtension.toUpperCase());
            } catch (final IllegalArgumentException e) {
                // Silently catch trouble and return the NONE image extension
            }
        }

        return ext;
    }

}
