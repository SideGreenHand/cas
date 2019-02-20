package com.jw.cas.web.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SsoProperties {

    public static String tokenUrl;

    @Value("${cas.tokenUrl}")
    public void setTokenUrl(String tokenUrl) {
        SsoProperties.tokenUrl = tokenUrl;
    }

}
