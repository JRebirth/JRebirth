package org.jrebirth.af.rest.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jrebirth.af.api.annotation.OnRelease;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.service.AbstractService;
import org.jrebirth.af.rest.RestParameters;

public class AbstractRestService extends AbstractService {

    private Client client;

    private WebTarget webTarget;

    @Override
    protected void initService() {
        client = ClientBuilder.newClient();

        client.target(RestParameters.DEFAULT_REST_SERVER.get());
    }

    @Override
    protected void processWave(Wave wave) {
        // Nothing to do yet

    }

    @Override
    protected void initInnerComponents() {
        // Nothing to do yet

    }

    @OnRelease
    public void closeClient() {
        client.close();
    }

    /**
     * @return Returns the webTarget.
     */
    protected WebTarget getWebTarget() {
        return webTarget;
    }
}
