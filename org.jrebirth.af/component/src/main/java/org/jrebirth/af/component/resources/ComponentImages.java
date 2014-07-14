package org.jrebirth.af.component.resources;

import org.jrebirth.af.core.resource.image.ImageEnum;
import org.jrebirth.af.core.resource.image.ImageExtension;
import org.jrebirth.af.core.resource.image.LocalImage;

public enum ComponentImages implements ImageEnum {

    DefaultDockableIcon {
        {
            set(new LocalImage("DefaultDockableIcon", ImageExtension.PNG));
        }
    }

}
