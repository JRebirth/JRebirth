package org.jrebirth.af.component.ui.stack;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.WaveType;
import org.jrebirth.af.core.wave.WaveItemBase;
import org.jrebirth.af.core.wave.WaveTypeBase;

/**
 * The class <strong>StackWaves</strong>.
 *
 * All {@link WaveItemBase} and {@link WaveType} used to manage the stack model.
 *
 * @author Sébastien Bordes
 */
public interface StackWaves {

    /*****************************************************************************************************/
    /** _________________________________________Wave Items.____________________________________________ */
    /*****************************************************************************************************/

    /** The name of the stack concerned. */
    WaveItemBase<String> STACK_NAME = new WaveItemBase<String>() {
    };

    /** The Enum class that list all pages displayable into the stack. */
    // WaveItem<Class<? extends PageEnum>> STACK_PAGES = new WaveItem<Class<? extends PageEnum>>() {
    // };

    /** The page to display (model class descriptor). */
    WaveItemBase<UniqueKey<? extends Model>> PAGE_MODEL_KEY = new WaveItemBase<UniqueKey<? extends Model>>() {
    };

    /** The page to display (enum descriptor). */
    WaveItemBase<PageEnum> PAGE_ENUM = new WaveItemBase<PageEnum>() {
    };

    /*****************************************************************************************************/
    /** _________________________________________Wave Types.____________________________________________ */
    /*****************************************************************************************************/

    /** Show Page (with Model) action. */
    WaveType SHOW_PAGE_MODEL = WaveTypeBase.create("SHOW_PAGE_MODEL").items(PAGE_MODEL_KEY, STACK_NAME);

    /** Show Page (with Enum) action. */
    WaveType SHOW_PAGE_ENUM = WaveTypeBase.create("SHOW_PAGE_ENUM").items(PAGE_ENUM/* , STACK_PAGES */);

}
