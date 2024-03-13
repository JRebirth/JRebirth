package org.jrebirth.af.rest.service;

import jakarta.ws.rs.client.Client;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.service.Service;

@RegistrationPoint
public interface ClientProviderService extends Service {

    Client client();

}
