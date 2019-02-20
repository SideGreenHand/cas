package com.jw.cas.web.cookie;

import org.apereo.cas.CipherExecutor;
import org.apereo.cas.web.support.CookieRetrievingCookieGenerator;
import org.apereo.cas.web.support.CookieValueManager;
import org.apereo.cas.web.support.NoOpCookieValueManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TgcCookieGenerator extends CookieGenerator {

    CookieValueManager cookieValueManager;

    public TgcCookieGenerator(CookieRetrievingCookieGenerator generator,CipherExecutor executor) {
        super.setCookieName(generator.getCookieName());
        super.setCookiePath(generator.getCookiePath());
        this.setCookieDomain(generator.getCookieDomain());
        super.setCookieMaxAge(generator.getCookieMaxAge());
        super.setCookieSecure(generator.isCookieSecure());
        super.setCookieHttpOnly(generator.isCookieHttpOnly());

        cookieValueManager = new NoOpCookieValueManager();
    }

    public void addCookie(String tgt) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String theCookieValue = this.cookieValueManager.buildCookieValue(tgt, request);
        super.addCookie(response, theCookieValue);
    }

    public void delCookie(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        super.removeCookie(response);
    }
}
