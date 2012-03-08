package org.jrebirth.core.ui;

import org.jrebirth.core.facade.UniqueKey;

/**
 * The interface <strong>InnerModels</strong>.
 * 
 * You may create an enumeration that implements this interface to manage inner models inside a root model.
 * 
 * @author Sébastien Bordes
 * 
 */
public interface InnerModels {

    /**
     * Return the class model.
     * 
     * @return the class of the model
     */
    Class<? extends Model> getModelClass();

    /**
     * Return the key of the model. Can return null if the model is unique
     * 
     * @return the multiton key if any, or null
     */
    UniqueKey getKey();

}
