package org.jrebirth.af.sample.resources;


import org.jrebirth.af.core.resource.font.FontItem;
import org.jrebirth.af.core.resource.font.RealFont;

import static org.jrebirth.af.core.resource.Resources.create;

/**
 * The class <strong>SampleFonts</strong>.
 * 
 * @author
 */
public interface SampleFonts {

    /** The splash font. */
    FontItem SPLASH = create(new RealFont(SampleFontNames.DINk, 24));

}
