package com.integration.pokemon.api.domain.interceptors;

import com.integration.pokemon.api.Constants;
import com.integration.pokemon.api.persistence.entity.SoapServicesHistoryEntity;
import com.integration.pokemon.api.persistence.services.SoapServicesHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;

@Component
public class GeneralSoapServicesInterceptor extends EndpointInterceptorAdapter {
    Logger LOG = LoggerFactory.getLogger(GeneralSoapServicesInterceptor.class);

    @Autowired
    private SoapServicesHistoryService soapServicesHistoryService;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        var endPointInfo = (MethodEndpoint) endpoint;
        var method = endPointInfo.getMethod();
        var isTrackingSaved = soapServicesHistoryService.save(
                new SoapServicesHistoryEntity(0, Constants.SoapService.DEFAULT_IP_ORIGIN,method.getName()));

        if (isTrackingSaved){
            LOG.info("Track Created, Method:{}", method.getName());
        } else {
            LOG.warn("Error to created tracking, Method:{}", method.getName());
        }

        return true;
    }

}
