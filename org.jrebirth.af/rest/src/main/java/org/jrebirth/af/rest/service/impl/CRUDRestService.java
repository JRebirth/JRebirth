package org.jrebirth.af.rest.service.impl;

import java.util.List;

import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.core.wave.WaveItemBase;

public interface CRUDRestService<O extends Object> extends Service {

    /** The Wave Item Element. */
    WaveItem<Object> ELEMENT = new WaveItemBase<Object>() {
    };

    String CREATE_OBJECT_PREFIX = "CREATE_OBJECT_";

    String OBJECT_CREATED_PREFIX = "OBJECT_CREATED_";

    // WaveType DO_CREATE_OBJECT = WBuilder.waveType(CREATE_OBJECT)
    // .returnAction(OBJECT_CREATED)
    // .items(ELEMENT)
    // .returnItem(JRebirthItems.booleanItem);

    String UPDATE_OBJECT_PREFIX = "UPDATE_OBJECT_";

    String OBJECT_UPDATED_PREFIX = "OBJECT_UPDATED_";

    // WaveType DO_UPDATE_OBJECT = WBuilder.waveType(UPDATE_OBJECT)
    // .returnAction(OBJECT_UPDATED)
    // .items(ELEMENT)
    // .returnItem(JRebirthItems.booleanItem);

    // @OnWave(CREATE_OBJECT)
    // @Priority(PriorityLevel.Normal)
    boolean doCreateObject(O object, final Wave wave);

    // @OnWave(UPDATE_OBJECT)
    // @Priority(PriorityLevel.Normal)
    boolean doUpdateObject(O object, final Wave wave);

    // @OnWave(CREATE_OBJECT)
    // @Priority(PriorityLevel.Normal)
    boolean doDeleteObject(O object, final Wave wave);

    // @OnWave(CREATE_OBJECT)
    // @Priority(PriorityLevel.Normal)
    O doGetObject(Integer id, final Wave wave);

    // @OnWave(CREATE_OBJECT)
    // @Priority(PriorityLevel.Normal)
    List<O> doGetObjects(final Wave wave);

}
