package com.jw.cas.web.cookie;

import org.apereo.cas.CentralAuthenticationService;
import org.apereo.cas.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TgcCookieController{

    @Autowired
    @Qualifier("centralAuthenticationService")
    private CentralAuthenticationService centralAuthenticationService;

    @Autowired
    private TgcCookieGenerator TgcCookieGenerator;

    @GetMapping("/setCookie")
    public String setCookie(@RequestParam("service") String service, @RequestParam("tgt") String tgt) {
        try {
            // 校验
            Ticket ticket = this.centralAuthenticationService.getTicket(tgt, Ticket.class);
            if (ticket == null || ticket.isExpired()) {
                // 无效tgt，跳转到登录
                return "redirect:/login";
            }
            // 添加cookie
            TgcCookieGenerator.addCookie(tgt);
            // 跳转到客户端
            return "redirect:" + service;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/delCookie")
    public String delCookie() {
        try {
            // 添加cookie
            TgcCookieGenerator.delCookie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}
