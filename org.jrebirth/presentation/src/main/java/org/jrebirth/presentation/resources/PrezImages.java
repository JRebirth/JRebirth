/**
 * Get more info at : www.jrebirth.org .
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
package org.jrebirth.presentation.resources;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.image.ImageExtension;
import org.jrebirth.core.resource.image.ImageItem;
import org.jrebirth.core.resource.image.LocalImage;

/**
 * The MTImages interface providing all images.
 */
public interface PrezImages {

    /** The application main title image. */
    ImageItem MT_TITLE = create(new LocalImage("Title", ImageExtension.PNG));

    /** Monster used into Result page (Monster is reading a book). */
    ImageItem RESULT_MONSTER = create(new LocalImage("Result_Monster", ImageExtension.PNG));

    /** The success icon used to count a good answer. */
    ImageItem RESULT_SUCCESS_ICON = create(new LocalImage("Result_Success_Icon", ImageExtension.PNG));

    /** The failure icon used to count a bad answer. */
    ImageItem RESULT_FAILURE_ICON = create(new LocalImage("Result_Failure_Icon", ImageExtension.PNG));
}
