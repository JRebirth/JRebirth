package org.jrebirth.analyzer.ui.workbench;

import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.AbstractModel;

/**
 * The class <strong>WorkbenchModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class WorkbenchModel extends AbstractModel<WorkbenchModel, WorkbenchView> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {

        // Do stuff on the model !
        getInnerModel(WorkbenchInnerModels.CONTROLS);
        getInnerModel(WorkbenchInnerModels.PROPERTIES);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }

}
