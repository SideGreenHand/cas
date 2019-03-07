package com.jw.cas.web.configurer;

import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.web.flow.InitializeLoginAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class NewInitializeLoginAction extends AbstractAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeLoginAction.class);
    protected ServicesManager servicesManager;

    public NewInitializeLoginAction(ServicesManager servicesManager) {
        this.servicesManager = servicesManager;
    }

    protected Event doExecute(RequestContext requestContext) throws Exception {
        LOGGER.debug("NewInitialized login sequence");
        return this.success();
    }
}
