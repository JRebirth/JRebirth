package org.jrebirth.analyzer.ui.properties;

import org.jrebirth.core.link.WaveItem;

/**
 * The class <strong>PropertiesWaveItem</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum PropertiesWaveItem implements WaveItem {

    /** The event object. */
    EVENT_OBJECT() {

        @Override
        public Class<?> dataClass() {
            return null;
        }

    },

    /** The type of the node. */
    NODE_TYPE() {

        @Override
        public Class<?> dataClass() {
            return null;
        }

    },

}
