package com.integration.pokemon.api.domain.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;

@Component
public class GeneralSoapServicesInterceptor extends EndpointInterceptorAdapter {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        var endPointInfo = (MethodEndpoint) endpoint;
        var method = endPointInfo.getMethod();
        System.out.println("Called Method: " + method.getName());
        return true;
    }

}
