#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.resources;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.core.resource.font.FontItem;
import org.jrebirth.af.core.resource.font.RealFont;

/**
 * The class <strong>SampleFonts</strong>.
 * 
 * @author
 */
public interface SampleFonts {

    /** The splash font. */
    FontItem SPLASH = create(new RealFont(SampleFontNames.DINk, 24));

}
