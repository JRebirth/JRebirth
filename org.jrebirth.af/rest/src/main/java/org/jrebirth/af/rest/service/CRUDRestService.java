package org.jrebirth.af.rest.service;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jrebirth.af.api.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CRUDRestService<O extends Object> extends AbstractRestService {

    // /** The Wave Item ALL_EXPRESSIONS. */
    // public static final WaveItem<List<O>> PLANETS = new WaveItemBase<List<O>>() {
    // };
    //
    // /** The WaveType return action name. */
    // public static final String PLANETS_LIST = "PLANETS_LIST";
    //
    // /** The Wave Type DO_BUILD_TABLES. */
    // public static final WaveType DO_GET_PLANETS = Builders.waveType("GET_PLANETS")
    // .returnAction(PLANETS_LIST)
    // .returnItem(PLANETS);

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CRUDRestService.class);

    private final String crudPath = "planets";

    private final GenericType<O> genericSingle = new GenericType<O>() {
    };
    private final GenericType<List<O>> genericList = new GenericType<List<O>>() {
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebTarget getWebTarget() {
        return super.getWebTarget().path(getCRUDPath());
    }

    public String getCRUDPath() {
        return crudPath;
    }

    /**
     * .
     * 
     * @param wave the source wave
     */
    public boolean doCreateObject(O object, final Wave wave) {
        final Response updateResponse = getWebTarget()
                                                      .request(MediaType.APPLICATION_XML)
                                                      .post(Entity.xml(object));

        return updateResponse.getStatusInfo() == Status.OK;
    }

    /**
     * .
     * 
     * @param wave the source wave
     */
    public boolean doUpdateObject(O object, final Wave wave) {

        final Response updateResponse = getWebTarget()
                                                      .request(MediaType.APPLICATION_XML)
                                                      .put(Entity.xml(object));

        return updateResponse.getStatusInfo() == Status.OK;
    }

    /**
     * .
     * 
     * @param wave the source wave
     */
    public boolean doDeleteObject(O object, final Wave wave) {

        LOGGER.trace("Delete Object.");

        final Response deleteResponse = getWebTarget().path("1"/* object.id() */)
                                                      .request(MediaType.APPLICATION_XML)
                                                      .delete();

        return deleteResponse.getStatusInfo() == Status.OK;
    }

    /**
     * .
     * 
     * @param wave the source wave
     */
    public O doGetObject(Integer id, final Wave wave) {

        LOGGER.trace("Get Planet.");

        final O object = getWebTarget()
                                       .request(MediaType.APPLICATION_XML)
                                       .get(genericSingle);

        return object;

    }

    /**
     * .
     * 
     * @param wave the source wave
     */
    public List<O> doGetObjects(final Wave wave) {

        LOGGER.trace("Get Planets.");

        final List<O> objects = getWebTarget()
                                              .request(MediaType.APPLICATION_XML)
                                              .get(genericList);

        return objects;

    }

}
