package org.jrebirth.core.link;

import java.util.List;

import org.jrebirth.core.link.impl.WaveData;

/**
 * The interface <strong>Wave</strong>.
 * 
 * A Wave is a message which contain a type and a map of object.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface Wave {

    /**
     * @return Returns the Wave Unique Identifier.
     */
    String getWUID();

    /**
     * @return Returns the Wave timestamp (for creation).
     */
    long getTimestamp();

    /**
     * @return Returns the waveGroup.
     */
    WaveGroup getWaveGroup();

    /**
     * @param waveGroup The waveGroup to set.
     */
    void setWaveGroup(WaveGroup waveGroup);

    /**
     * @return Returns the waveType.
     */
    WaveType getWaveType();

    /**
     * @param waveType The waveType to set.
     */
    void setWaveType(WaveType waveType);

    /**
     * @return Returns the relatedClass.
     */
    Class<?> getRelatedClass();

    /**
     * @param relatedClass The relatedClass to set.
     */
    void setRelatedClass(Class<?> relatedClass);

    /**
     * @return Returns the priority.
     */
    int getPriority();

    /**
     * @param priority The priority to set.
     */
    void setPriority(int priority);

    /**
     * Return all wave items sorted by order.
     * 
     * @return Returns the the wave items.
     */
    List<WaveData> getWaveItems();

    /**
     * Store an object and indexize it.
     * 
     * @param waveItem the waveItem of the object to add
     * @param waveData the object to store
     * 
     */
    void add(WaveItem waveItem, WaveData waveData);

    /**
     * Retrieve an object.
     * 
     * @param waveItem the waveItem of the object to retrieve
     * 
     * @return the waveData registered by the key
     */
    WaveData get(WaveItem waveItem);
}
