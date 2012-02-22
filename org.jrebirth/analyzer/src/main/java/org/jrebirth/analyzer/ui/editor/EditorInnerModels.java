package org.jrebirth.analyzer.ui.editor;

import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.facade.UniqueKey;
import org.jrebirth.core.ui.InnerModels;
import org.jrebirth.core.ui.Model;

/**
 * The enumeration <strong>EditorInnerModels</strong>.
 * 
 * Declare inner models contained by EditorModel
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum EditorInnerModels implements InnerModels {

    /** The controls UI. */
    BALLS(BallModel.class);

    // /** The properties UI. */
    // WAVE(WaveModel.class)

    /** The model class of the inner model. */
    private final Class<? extends Model> modelClass;

    /**
     * Default Constructor.
     * 
     * @param modelClass the class to set
     */
    EditorInnerModels(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey getKey() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getModelClass() {
        return this.modelClass;
    }

}
