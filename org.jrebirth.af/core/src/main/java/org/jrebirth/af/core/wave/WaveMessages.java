package org.jrebirth.af.core.wave;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

public interface WaveMessages extends MessageContainer {

    /** RelatedWaveListener. */

    /** "{0} Consumes wave {1}" . */
    MessageItem RELATED_WAVE_HANDLES = create(new LogMessage("jrebirth.wave.relatedWaveHandles", JRLevel.Trace, JRebirthMarkers.WAVE));

    /** "{0} experience a service task failure for wave {1}" . */
    MessageItem RELATED_WAVE_HAS_FAILED = create(new LogMessage("jrebirth.wave.relatedWaveHasFailed", JRLevel.Error, JRebirthMarkers.WAVE));
    
}