package org.jrebirth.presentation;

import javafx.scene.text.Font;

import org.jrebirth.core.resource.ResourceFactories;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.resource.font.FontFactory;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.font.RealFont;

/**
 * The class <strong>PrezFonts</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum PrezFonts implements FontEnum {

    /** The splash font. */
    PAGE(new RealFont(FontsLoader.DINk, 24)),

    /** The slide title font. */
    SLIDE_TITLE(new RealFont(FontsLoader.Harabara, 36)),

    /** The slide subtitle font. */
    SLIDE_SUBTITLE(new RealFont(FontsLoader.Harabara, 24)),

    /** The typewriter font. */
    TYPEWRITER(new RealFont(FontsLoader.Segoe_UI, 72)),

    /** The splash font. */
    SPLASH(new RealFont(FontsLoader.BorisBlackBloxx, 40));

    /**
     * Default Constructor.
     * 
     * @param fontParams the font size
     */
    PrezFonts(final FontParams fontParams) {
        factory().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontFactory factory() {
        return (FontFactory) ResourceFactories.FONT_FACTORY.use();
    }

}
