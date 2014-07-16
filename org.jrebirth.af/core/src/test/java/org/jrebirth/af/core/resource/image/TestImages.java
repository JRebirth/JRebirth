/**
 b * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.af.core.resource.image;

import org.jrebirth.af.core.resource.image.ImageExtension;
import org.jrebirth.af.core.resource.image.ImageItem;
import org.jrebirth.af.core.resource.image.LocalImage;
import org.jrebirth.af.core.resource.image.WebImage;

import static org.jrebirth.af.core.resource.Resources.create;

/**
 * The class <strong>TestColors</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface TestImages {

    /**************************************************************************************/
    /** __________________________________Local Image.___________________________________ */
    /**************************************************************************************/

    /** The local image. */
    ImageItem TEST_LOCAL_IMAGE_1 = create(new LocalImage("path1", "logo", ImageExtension.PNG));

    /** The local image. */
    ImageItem TEST_LOCAL_IMAGE_2 = create(new LocalImage("path1/path2", "logo", ImageExtension.PNG));

    /** The local image. */
    ImageItem TEST_LOCAL_IMAGE_3 = create(new LocalImage("logo", ImageExtension.PNG));
    
    /** The local image. */
    ImageItem TEST_LOCAL_IMAGE_4 = create(new LocalImage("logoBiss", ImageExtension.PNG));

    /**************************************************************************************/
    /** ___________________________________Web Image.____________________________________ */
    /**************************************************************************************/

    /** The web image. */
    ImageItem TEST_WEB_IMAGE_1 = create(new WebImage("www.jrebirth.org", "", "", ImageExtension.PNG));

    /** The web image. */
    ImageItem TEST_WEB_IMAGE_2 = create(new WebImage("www.jrebirth.org", "", "", ImageExtension.PNG));

    /** The web image. */
    ImageItem TEST_WEB_IMAGE_3 = create(new WebImage("www.jrebirth.org", true, "", "", ImageExtension.PNG));

}
