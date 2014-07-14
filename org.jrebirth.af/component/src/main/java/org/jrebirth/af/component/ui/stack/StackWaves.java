package org.jrebirth.af.component.ui.stack;

import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.WaveItem;
import org.jrebirth.af.core.wave.WaveType;

/**
 * The class <strong>StackWaves</strong>.
 *
 * All {@link WaveItem} and {@link WaveType} used to manage the stack model.
 *
 * @author Sébastien Bordes
 */
public interface StackWaves {

    /*****************************************************************************************************/
    /** _________________________________________Wave Items.____________________________________________ */
    /*****************************************************************************************************/

    /** The name of the stack concerned. */
    WaveItem<String> STACK_NAME = new WaveItem<String>() {
    };

    /** The Enum class that list all pages displayable into the stack. */
    // WaveItem<Class<? extends PageEnum>> STACK_PAGES = new WaveItem<Class<? extends PageEnum>>() {
    // };

    /** The page to display (model class descriptor). */
    WaveItem<UniqueKey<? extends Model>> PAGE_MODEL_KEY = new WaveItem<UniqueKey<? extends Model>>() {
    };

    /** The page to display (enum descriptor). */
    WaveItem<PageEnum> PAGE_ENUM = new WaveItem<PageEnum>() {
    };

    /*****************************************************************************************************/
    /** _________________________________________Wave Types.____________________________________________ */
    /*****************************************************************************************************/

    /** Show Page (with Model) action. */
    WaveType SHOW_PAGE_MODEL = WaveType.create("SHOW_PAGE_MODEL").items(PAGE_MODEL_KEY, STACK_NAME);

    /** Show Page (with Enum) action. */
    WaveType SHOW_PAGE_ENUM = WaveType.create("SHOW_PAGE_ENUM").items(PAGE_ENUM/* , STACK_PAGES */);

}
