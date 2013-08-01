package org.jrebirth.component.ui.stack;

import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveType;
import org.jrebirth.core.wave.WaveTypeBase;

public interface StackWaves {

    /** The page to display. */
    WaveItem<UniqueKey<? extends Model>> PAGE_MODEL_KEY = new WaveItem<UniqueKey<? extends Model>>() {
    };

    /** The page to display. */
    WaveItem<PageEnum> PAGE_ENUM = new WaveItem<PageEnum>() {
    };

    /** Show Page action. */
    WaveType SHOW_PAGE_MODEL = WaveTypeBase.build("SHOW_PAGE_MODEL", PAGE_MODEL_KEY);

    /** Show Page action. */
    WaveType SHOW_PAGE_ENUM = WaveTypeBase.build("SHOW_PAGE_ENMU", PAGE_ENUM);

}
