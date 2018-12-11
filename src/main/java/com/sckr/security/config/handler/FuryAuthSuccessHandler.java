package com.sckr.security.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sckr.security.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理登录验证成功的类
 * @author zhoukebo
 * @date 2018/9/4
 */
@Component
public class FuryAuthSuccessHandler implements AuthenticationSuccessHandler {
    /**Json转化工具*/
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException{
        SysUser userDetails = (SysUser)authentication.getPrincipal();
        System.out.println("管理员 " + userDetails.getUsername() + " 登录");
        Map<String,String> map=new HashMap<>(2);
        map.put("code", "200");
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }

}