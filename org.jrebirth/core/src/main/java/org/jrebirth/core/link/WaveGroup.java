package org.jrebirth.core.link;

/**
 * The enumeration <strong>WaveGroup</strong>.
 * 
 * Alow to classify waves by group.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public enum WaveGroup {

    /** Used to call a command. */
    CALL_COMMAND,

    /** Used to attach an UI into a nodeProperty. */
    ATTACH_UI,

    /** Used to return Data from Service. */
    RETURN_DATA,

    /** Used by Other Waves. */
    UNDEFINED
}
