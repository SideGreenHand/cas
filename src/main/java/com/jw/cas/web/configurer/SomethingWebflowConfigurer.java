package com.jw.cas.web.configurer;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.configurer.AbstractCasWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.ActionState;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;


public class SomethingWebflowConfigurer extends AbstractCasWebflowConfigurer {

    public SomethingWebflowConfigurer(FlowBuilderServices flowBuilderServices,
                                      FlowDefinitionRegistry flowDefinitionRegistry,
                                      ApplicationContext applicationContext,
                                      CasConfigurationProperties casProperties){
        super(flowBuilderServices,flowDefinitionRegistry,applicationContext,casProperties);
    }

    @Override
    protected void doInitialize() {
        final Flow flow = super.getLoginFlow();

        ActionState initializeLoginForm = (ActionState) flow.getState("initializeLoginForm");
    }

}
