package org.jrebirth.af.rest.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jrebirth.af.api.annotation.OnRelease;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.service.AbstractService;
import org.jrebirth.af.rest.service.ClientProviderService;

@Register(ClientProviderService.class)
public class SimpleClientProviderService extends AbstractService implements ClientProviderService {

    private Client client;

    @Override
    public Client client() {
        return client;
    }

    @Override
    protected void initService() {
        client = ClientBuilder.newClient();
    }

    /**
     * When the service is released close the client.
     */
    @OnRelease
    public void closeClient() {
        client.close();
    }

    @Override
    protected void processWave(Wave wave) {
        // Nothing to do yet
    }

    @Override
    protected void initInnerComponents() {
        // Nothing to do yet
    }

}
