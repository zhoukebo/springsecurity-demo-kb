package com.sckr.security.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sckr.security.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理验证成功
 * @author zhoukebo
 * @date 2018/9/4
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    /**Json转化工具*/
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        Map<String,String> map=new HashMap<>(2);
        map.put("code", "200");
        map.put("msg", "登出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}