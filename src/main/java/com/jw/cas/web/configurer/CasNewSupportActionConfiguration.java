package com.jw.cas.web.configurer;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.web.flow.InitializeLoginAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.webflow.execution.Action;

@Configuration("casNewSupportActionsConfiguration")
@EnableConfigurationProperties({CasConfigurationProperties.class})
@EnableTransactionManagement(
        proxyTargetClass = true
)
public class CasNewSupportActionConfiguration {

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @ConditionalOnMissingBean(
            name = {"newInitializeLoginAction"}
    )
    @Bean
    @RefreshScope
    public Action newInitializeLoginAction() {
        return new NewInitializeLoginAction(this.servicesManager);
    }

}
