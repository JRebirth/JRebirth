package org.jrebirth.af.component.ui.stack;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;

/**
 * The class <strong>PageEnum</strong>.
 *
 * Each enumerated item must return a UniqueKey
 *
 * @author Sébastien Bordes
 */
public interface PageEnum {

    /**
     * Return the unique key used to attach a {@link Model}.
     *
     * @return the model key associated to this item
     */
    UniqueKey<? extends Model> getModelKey();

}
