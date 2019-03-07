package com.jw.cas.web.login;

import com.jw.cas.web.cookie.TgcCookieGenerator;
import com.jw.cas.web.utils.CasServerUtil;
import org.apereo.cas.CentralAuthenticationService;
import org.apereo.cas.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private TgcCookieGenerator TgcCookieGenerator;

    @Autowired
    @Qualifier("centralAuthenticationService")
    private CentralAuthenticationService centralAuthenticationService;

    @GetMapping("/reg")
    public String reg(){
        return "register";
    }

    @PostMapping("/rest/login")
    @ResponseBody
    public String restLogin(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username：" + username + "，password：" + password );


        // 1、获取 TGT
        String tgt = CasServerUtil.getTGT(username, password);
        if (tgt != null){
            try {
                // 校验
                Ticket ticket = this.centralAuthenticationService.getTicket(tgt, Ticket.class);
                if (ticket == null || ticket.isExpired()) {
                    // 无效tgt，跳转到登录
                    return "{\"code\":\"40001\"}";
                }
                // 添加cookie
                TgcCookieGenerator.addCookie(tgt);
                // 跳转到客户端
                return "{\"code\":\"0\"}";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "{\"code\":\"40001\"}";
    }
}
