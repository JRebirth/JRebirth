package org.jrebirth.af.rest.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jrebirth.af.api.annotation.OnRelease;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.service.AbstractService;
import org.jrebirth.af.rest.RestParameters;
import org.jrebirth.af.rest.annotation.RestPath;
import org.jrebirth.af.rest.annotation.RestTarget;

public abstract class AbstractRestService extends AbstractService {

    private Client client;

    private WebTarget baseWebTarget;

    private String localPath;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initService() {

        final RestTarget rt = this.getClass().getAnnotation(RestTarget.class);

        client = ClientBuilder.newClient();

        final StringBuilder url = new StringBuilder();
        if (rt != null && rt.value() != null) {
            url.append(rt.value());
        } else {

            url.append(rt.protocol() != null ? rt.protocol().name() : RestParameters.DEFAULT_REST_SERVER_PROTOCOL.get())
               .append("://")
               .append(rt.address() != null ? rt.address() : RestParameters.DEFAULT_REST_SERVER_ADDRESS.get())
               .append(":")
               .append(rt.port() > 0 ? rt.port() : RestParameters.DEFAULT_REST_SERVER_PORT.get())
               .append("/")
               .append(rt.path() != null ? rt.path() : RestParameters.DEFAULT_REST_SERVER_PATH.get());
        }
        baseWebTarget = client.target(url.toString());

        final RestPath rp = this.getClass().getAnnotation(RestPath.class);
        if (rp != null && rp.value() != null) {
            localPath = rp.value();
        }

        initWave();

    }

    protected abstract void initWave();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do yet
    }

    /**
     * When the service is released close the client.
     */
    @OnRelease
    public void closeClient() {
        client.close();
    }

    /**
     * @return Returns the baseWebTarget.
     */
    protected WebTarget baseWebTarget() {
        return baseWebTarget;
    }

    /**
     * @return Returns the webTarget.
     */
    protected WebTarget webTarget() {
        return baseWebTarget().path(localPath());
    }

    /**
     * @return the localPath used by this rest service.
     */
    public String localPath() {
        return localPath;
    }

    /**
     * @param localPath the localPath to set
     */
    protected void localPath(String localPath) {
        this.localPath = localPath;
    }

}
