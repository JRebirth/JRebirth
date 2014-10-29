package org.jrebirth.af.api.ui.object;

import org.jrebirth.af.api.ui.Model;

public interface ModelObject<O> extends Model {

    /**
     * Return the bindable object and create it if null.
     *
     * @return the bindable object
     */
    O getObject();

    /**
     * @param object The object to set.
     */
    void setObject(final O object);

}
