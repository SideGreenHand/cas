package com.jw.cas.web.cookie;

import org.apereo.cas.CipherExecutor;
import org.apereo.cas.web.support.CookieRetrievingCookieGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomizeConfiguration {

    @Autowired
    CipherExecutor cookieCipherExecutor;

    @Autowired
    @Qualifier("ticketGrantingTicketCookieGenerator")
    private CookieRetrievingCookieGenerator generator;

    @Bean
    TgcCookieGenerator TgcCookieGenerator(){
        return new TgcCookieGenerator(generator, cookieCipherExecutor);
    }
}
