package org.jrebirth.analyzer.ui.properties;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.AbstractModel;

/**
 * The class <strong>PropertiesModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 55 $ $Author: sbordes $
 * @since $Date: 2011-10-14 19:23:59 +0200 (Fri, 14 Oct 2011) $
 */
public final class PropertiesModel extends AbstractModel<PropertiesModel, PropertiesView> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        listen(EditorWave.EVENT_SELECTED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        if (EditorWave.EVENT_SELECTED == wave.getWaveType()) {

            final Event event = (Event) wave.get(PropertiesWaveItem.EVENT_OBJECT).getValue();
            getView().getNodeName().setText(event.getTarget().getSimpleName());
        }
    }
}
