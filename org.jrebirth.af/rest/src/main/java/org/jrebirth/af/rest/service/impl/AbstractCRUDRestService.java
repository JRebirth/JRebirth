package org.jrebirth.af.rest.service.impl;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.JRebirthItems;
import org.jrebirth.af.core.wave.WBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractCRUDRestService<O extends Object> extends AbstractRestService implements CRUDRestService<O> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCRUDRestService.class);

    private final String crudPath = "planets";

    private final GenericType<O> genericSingle = new GenericType<O>() {
    };

    private final GenericType<List<O>> genericList = new GenericType<List<O>>() {
    };

    @Override
    public String localPath() {
        return crudPath;
    }

    public Class<O> objectClass() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initWave() {

        final WaveType DO_CREATE_OBJECT = WBuilder.waveType(CREATE_OBJECT_PREFIX + objectClass().getName())
                                                  .returnAction(OBJECT_CREATED_PREFIX + objectClass().getName())
                                                  .items(ELEMENT)
                                                  .returnItem(JRebirthItems.booleanItem);

        listen(DO_CREATE_OBJECT);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doCreateObject(O object, final Wave wave) {
        final Response updateResponse = baseWebTarget()
                                                       .request(MediaType.APPLICATION_XML)
                                                       .post(Entity.xml(object));

        return updateResponse.getStatusInfo() == Status.OK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doUpdateObject(O object, final Wave wave) {

        final Response updateResponse = baseWebTarget()
                                                       .request(MediaType.APPLICATION_XML)
                                                       .put(Entity.xml(object));

        return updateResponse.getStatusInfo() == Status.OK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doDeleteObject(Object object, final Wave wave) {

        LOGGER.trace("Delete Object.");

        final Response deleteResponse = baseWebTarget().path("1"/* object.id() */)
                                                       .request(MediaType.APPLICATION_XML)
                                                       .delete();

        return deleteResponse.getStatusInfo() == Status.OK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public O doGetObject(Integer id, final Wave wave) {

        LOGGER.trace("Get Planet.");

        final O object = baseWebTarget()
                                        .request(MediaType.APPLICATION_XML)
                                        .get(genericSingle);

        return object;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<O> doGetObjects(final Wave wave) {

        LOGGER.trace("Get Planets.");

        final List<O> objects = baseWebTarget()
                                               .request(MediaType.APPLICATION_XML)
                                               .get(genericList);

        return objects;

    }

}
