package org.jrebirth.analyzer.ui.editor;

import org.jrebirth.core.link.WaveType;
import org.jrebirth.core.util.ClassUtility;

/**
 * The class <strong>EditorWave</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum EditorWave implements WaveType {

    /** Wave used when the events are loaded. */
    EVENTS_LOADED,

    /** . */
    PLAY,

    /** . */
    NEXT,

    /** . */
    PREVIOUS,

    /** . */
    STOP,

    /** Wave used to display info into the properties view. */
    EVENT_SELECTED;

    /** The action to launch. */
    private String action;

    /**
     * Default Constructor.
     */
    private EditorWave() {
        this.action = ClassUtility.underscoreToCamelCase(name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAction() {
        return this.action;
    }

}
