package org.jrebirth.analyzer.ui.workbench;

import org.jrebirth.analyzer.ui.controls.ControlsModel;
import org.jrebirth.analyzer.ui.editor.EditorModel;
import org.jrebirth.analyzer.ui.properties.PropertiesModel;
import org.jrebirth.core.facade.UniqueKey;
import org.jrebirth.core.ui.InnerModels;
import org.jrebirth.core.ui.Model;

/**
 * The enumeration <strong>WorkbenchInnerModels</strong>.
 * 
 * Declare inner models contained by WorkbenchModel
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum WorkbenchInnerModels implements InnerModels {

    /** The controls UI. */
    CONTROLS(ControlsModel.class),

    /** The properties UI. */
    PROPERTIES(PropertiesModel.class),

    /** The properties UI. */
    EDITOR(EditorModel.class);

    /** The model class of the inner model. */
    private final Class<? extends Model> modelClass;

    /**
     * Default Constructor.
     * 
     * @param modelClass the class to set
     */
    WorkbenchInnerModels(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getModelClass() {
        return this.modelClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey getKey() {
        return null;
    }

}
